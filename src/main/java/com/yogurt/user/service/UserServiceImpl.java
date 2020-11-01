package com.yogurt.user.service;

import com.yogurt.auth.domain.TokenBlacklist;
import com.yogurt.auth.domain.TokenBlacklistRepository;
import com.yogurt.auth.service.VerificationService;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.*;
import com.yogurt.base.security.JwtTokenProvider;
import com.yogurt.file.S3Uploader;
import com.yogurt.generic.user.domain.Email;
import com.yogurt.generic.user.domain.Phone;
import com.yogurt.generic.user.domain.VerificationType;
import com.yogurt.mail.service.MailService;
import com.yogurt.user.domain.User;
import com.yogurt.user.dto.common.ChangeEmailRequest;
import com.yogurt.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailService mailService;

    private final CryptoService cryptoService;

    private final VerificationService verificationService;

    private final JwtTokenProvider jwtTokenProvider;

    private final S3Uploader s3Uploader;

    private final UserRepository repository;

    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Transactional
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public User checkUser(User user) {
        return this.getById(user.getId());
    }

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        tokenBlacklistRepository.save(TokenBlacklist.of(token));
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
    public User changeUsername(Long id, String username) {
        User user = this.getById(id);
        if (user.getUsername().equals(username)) {
            throw new YogurtInvalidSameUsernameException("이전 아이디와 같은 아이디로 변경할 수 없습니다.");
        }
        if (existsByUsername(username)) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 아이디입니다.");
        }
        user.setUsername(username);
        User updatedUser = repository.save(user);
        changeUsernameSendMail(updatedUser);
        return updatedUser;
    }

    private void changeUsernameSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("username", user.getUsername());
        mailService.send("/change-username", dataMap, user.getName(), user.getEmail());
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
        changePasswordSendMail(updatedUser);
        return updatedUser;
    }

    private void changePasswordSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        mailService.send("/change-password", dataMap, user.getName(), user.getEmail());
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
        changeEmailSendMail(user, originalEmail, changingEmail);

        return updatedUser;
    }

    private void changeEmailSendMail(User user, String originalEmail, String changedEmail) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("originalEmail", originalEmail);
        dataMap.put("changedEmail", changedEmail);
        mailService.send("/change-email", dataMap, user.getName(), user.getEmail());
    }

    @Transactional
    public User changeRole(Long id, String role) {
        User user = this.getById(id);
        user.setRole(role);
        return repository.save(user);
    }

    public User getByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow(() -> new YogurtEntityNotFountException("등록되지 않은 아이디입니다."));
        return user;
    }

    public User getByEmail(String email) {
        User user = repository.findByEmail(Email.of(email)).orElseThrow(() -> new YogurtEntityNotFountException("해당 이메일로 등록된 유저가 없습니다."));
        return user;
    }

    public List<User> getByName(String name) {
        return repository.findByName(name);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(Email.of(email));
    }

    public List<User> getAllWithFilter(Pageable pageable, Boolean isExit) {
        return repository.getAllWithFilter(pageable, isExit);
    }

    public String getMaskingUsername(String username) {
        int length = username.length();

        return username.substring(0, length - 2) + "**";
    }

    public User exit(Long id, String exitReason) {
        User user = this.getById(id);
        user.setIsExit(true);
        user.setExitReason(exitReason);
        return repository.save(user);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
