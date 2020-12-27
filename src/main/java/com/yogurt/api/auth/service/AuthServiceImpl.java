package com.yogurt.api.auth.service;

import com.yogurt.api.auth.domain.TokenBlacklist;
import com.yogurt.api.auth.domain.TokenBlacklistRepository;
import com.yogurt.api.auth.dto.FindPasswordRequest;
import com.yogurt.api.auth.dto.LoginRequest;
import com.yogurt.api.auth.dto.LoginResponse;
import com.yogurt.api.auth.dto.SaveUserRequest;
import com.yogurt.api.mail.service.MailService;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.api.user.service.UserService;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.YogurtAlreadyDataUseException;
import com.yogurt.base.exception.YogurtNoAuthException;
import com.yogurt.base.exception.YogurtWrongPasswordException;
import com.yogurt.base.security.JwtTokenProvider;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.user.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final MailService mailService;

    private final VerificationService verificationService;

    private final CryptoService cryptoService;

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenBlacklistRepository tokenBlacklistRepository;

    private final UserRepository userRepository;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getByEmail(loginRequest.getEmail());

        boolean isSamePassword = cryptoService.compare(loginRequest.getPassword(), user.getPassword());
        if (!isSamePassword) {
            throw new YogurtWrongPasswordException("잘못된 비밀번호입니다.");
        }

        if (user.getIsDeleted()) {
            throw new YogurtNoAuthException("탈퇴된 회원입니다.");
        }

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        return LoginResponse.of(jwtToken, user);
    }

    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        tokenBlacklistRepository.save(TokenBlacklist.of(token));
    }

    @Transactional
    public User saveUser(SaveUserRequest saveUserRequest) {
        boolean existsEmail = userService.existsByEmail(saveUserRequest.getEmail());
        if (existsEmail) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 이메일입니다.");
        }

        verificationService.verifyEmail(saveUserRequest.getEmail(), saveUserRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.SIGNUP.name());

        String encryptedPassword = cryptoService.encode(saveUserRequest.getPassword());
        User user = saveUserRequest.toEntity(encryptedPassword);

        this.saveUserSendMail(user);

        return userRepository.save(user);
    }

    private void saveUserSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        mailService.send("/signup-user", dataMap, user.getName(), user.getEmail());
    }

    @Transactional
    public void findPassword(FindPasswordRequest findPasswordRequest) {
        String email = findPasswordRequest.getEmail();
        String verificationCode = findPasswordRequest.getVerificationCode();
        String password = findPasswordRequest.getPassword();

        verificationService.verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.name());

        User user = userService.getByEmail(email);
        userService.changePassword(user.getId(), password);
    }

    public String getRandomPassword() {
        return StringUtils.getUUID(12).replaceAll("-", "") + "A!";
    }
}
