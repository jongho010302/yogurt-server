package com.yogurt.api.user.service;

import com.yogurt.api.auth.service.VerificationService;
import com.yogurt.api.mail.service.MailService;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.studio.service.StudioService;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.dto.common.ChangeEmailRequest;
import com.yogurt.api.user.dto.common.CheckResponse;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.exception.YogurtEntityNotFountException;
import com.yogurt.base.exception.YogurtInvalidSameEmailException;
import com.yogurt.base.exception.YogurtInvalidSamePasswordException;
import com.yogurt.base.util.DateUtils;
import com.yogurt.file.S3Uploader;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.generic.user.domain.UserRole;
import com.yogurt.generic.user.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final StudioService studioService;

    private final CryptoService cryptoService;

    private final VerificationService verificationService;

    private final S3Uploader s3Uploader;

    private final UserRepository repository;

    private final UserMailService mailService;

    @Transactional
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public CheckResponse checkUser(User user, long studioId) {
        Studio studio = studioService.getById(studioId);
        return CheckResponse.of(user, studio);
    }

    @Transactional
    public User changeName(Long id, String name) {
        User user = this.getById(id);
        user.setName(name);
        return repository.save(user);
    }

    @Transactional
    public User changePhone(Long id, String phone) {
        User user = this.getById(id);
        user.setPhone(Phone.of(phone));
        return repository.save(user);
    }

    @Transactional
    public User changeProfile(Long id, MultipartFile multipartFile) {
        String profileUrl = s3Uploader.upload(multipartFile, "profile/" + id);
        User user = this.getById(id);
        user.setProfileUrl(profileUrl);
        return repository.save(user);
    }

    @Transactional
    public User changePassword(Long id, String password) {
        User user = this.getById(id);
        String originalPassword = user.getPassword();
        boolean isSamePassword = cryptoService.compare(password, originalPassword);
        if (isSamePassword) {
            throw new YogurtInvalidSamePasswordException("이전 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
        }

        user.setPassword(cryptoService.encode(password));
        User updatedUser = repository.save(user);
        mailService.changePasswordSendMail(updatedUser);
        return updatedUser;
    }

    @Transactional
    public User changeEmail(Long id, ChangeEmailRequest changeEmailRequest) {
        User user = this.getById(id);
        String originalEmail = user.getEmail();
        String changingEmail = changeEmailRequest.getEmail();
        boolean isSameEmail = originalEmail.equals(changingEmail);
        if (isSameEmail) {
            throw new YogurtInvalidSameEmailException("이전 이메일과 같은 이메일로 변경할 수 없습니다.");
        }

        verificationService.verifyEmail(changingEmail, changeEmailRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.name());
        user.setEmail(Email.of(changingEmail));
        User updatedUser = repository.save(user);
        mailService.changeEmailSendMail(user, originalEmail, changingEmail);

        return updatedUser;
    }

    @Transactional
    public User changeRole(Long id, UserRole.RoleEnum role) {
        User user = this.getById(id);
        user.setRole(role);
        return repository.save(user);
    }

    @Transactional
    public User delete(User user, String deleteReason) {
        user.deleted();
        user.setDeletedAt(DateUtils.getCurrentDate());
        user.setDeleteReason(deleteReason);
        return repository.save(user);
    }

    @Transactional
    public User delete(Long id, String deleteReason) {
        User user = this.getById(id);
        return delete(user, deleteReason);
    }

    public User getByEmail(String email) {
        User user = repository.findByEmail(Email.of(email)).orElseThrow(() -> new YogurtEntityNotFountException("해당 이메일로 등록된 유저가 없습니다."));
        return user;
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(Email.of(email));
    }

    public List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted) {
        return repository.getAllWithFilter(pageable, isDeleted);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
