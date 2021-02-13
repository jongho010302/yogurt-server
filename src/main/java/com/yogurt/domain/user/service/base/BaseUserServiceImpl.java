package com.yogurt.domain.user.service.base;

import com.yogurt.base.crypto.CryptoService;
import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.auth.service.VerificationService;
import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.studio.service.admin.AdminStudioService;
import com.yogurt.domain.studio.service.common.CommonStudioService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.common.ChangeEmailRequest;
import com.yogurt.domain.user.dto.common.CheckResponse;
import com.yogurt.domain.user.exception.SameEmailException;
import com.yogurt.domain.user.exception.SamePasswordException;
import com.yogurt.domain.user.infra.UserRepository;
import com.yogurt.domain.user.service.UserMailService;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.file.S3Uploader;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.generic.user.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BaseUserServiceImpl implements BaseUserService {

    private final CommonUserService commonUserService;

    private final CommonStudioService commonStudioService;

    private final CryptoService cryptoService;

    private final VerificationService verificationService;

    private final S3Uploader s3Uploader;

    private final UserRepository repository;

    private final UserMailService mailService;

    @Transactional
    public CheckResponse checkUser(AuthContext authContext) {
        User user = commonUserService.getById(authContext.getUser().getId());
        Studio studio = commonStudioService.getById(authContext.getStudioId());
        return CheckResponse.of(user, studio);
    }

    @Transactional
    public User changeName(Long id, String name) {
        User user = commonUserService.getById(id);
        user.setName(name);
        return repository.save(user);
    }

    @Transactional
    public User changePhone(Long id, String phone) {
        User user = commonUserService.getById(id);
        user.setPhone(Phone.of(phone));
        return repository.save(user);
    }

    @Transactional
    public User changeProfile(Long id, MultipartFile multipartFile) {
        String profileUrl = s3Uploader.upload(multipartFile, "profile/" + id);
        User user = commonUserService.getById(id);
        user.setProfileUrl(profileUrl);
        return repository.save(user);
    }

    @Transactional
    public User changePassword(Long id, String password) {
        User user = commonUserService.getById(id);
        String originalPassword = user.getPassword();
        boolean isSamePassword = cryptoService.compare(password, originalPassword);
        if (isSamePassword) {
            throw new SamePasswordException();
        }

        user.setPassword(cryptoService.encode(password));
        User updatedUser = repository.save(user);
        mailService.changePasswordSendMail(updatedUser);
        return updatedUser;
    }

    @Transactional
    public User changeEmail(Long id, ChangeEmailRequest changeEmailRequest) {
        User user = commonUserService.getById(id);
        String originalEmail = user.getEmail();
        String changingEmail = changeEmailRequest.getEmail();
        boolean isSameEmail = originalEmail.equals(changingEmail);
        if (isSameEmail) {
            throw new SameEmailException();
        }

        verificationService.verifyEmail(changingEmail, changeEmailRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.name());
        user.setEmail(Email.of(changingEmail));
        User updatedUser = repository.save(user);
        mailService.changeEmailSendMail(user, originalEmail, changingEmail);

        return updatedUser;
    }
}
