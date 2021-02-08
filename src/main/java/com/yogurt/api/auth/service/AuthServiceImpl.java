package com.yogurt.api.auth.service;

import com.yogurt.api.auth.domain.TokenBlacklist;
import com.yogurt.api.auth.domain.TokenBlacklistRepository;
import com.yogurt.api.auth.dto.*;
import com.yogurt.api.auth.dto.oauth.FacebookOAuthResponse;
import com.yogurt.api.auth.dto.oauth.GoogleOAuthResponse;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.studio.service.StudioService;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.infra.UserRepository;
import com.yogurt.api.user.service.UserService;
import com.yogurt.base.crypto.CryptoService;
import com.yogurt.base.exception.*;
import com.yogurt.base.security.JwtTokenProvider;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.user.domain.UserRole;
import com.yogurt.generic.user.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final StudioService studioService;

    private final UserService userService;

    private final VerificationService verificationService;

    private final CryptoService cryptoService;

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenBlacklistRepository tokenBlacklistRepository;

    private final UserRepository userRepository;

    private final OAuthService oauthService;

    private final AuthMailService authMailService;

    @Transactional
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        tokenBlacklistRepository.save(TokenBlacklist.of(token));
    }

    @Transactional
    public LoginResponse loginWithEmail(EmailLoginRequest emailLoginRequest) {
        User user = userService.getByEmail(emailLoginRequest.getEmail());

        validatePassword(emailLoginRequest.getPassword(), user.getPassword());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), emailLoginRequest.getStudioId());
        Studio studio = studioService.getById(emailLoginRequest.getStudioId());
        return LoginResponse.of(jwtToken, user, studio);
    }

    @Override
    public LoginResponse loginWithGoogle(SocialLoginRequest socialLoginRequest) {
        GoogleOAuthResponse response = oauthService.requestGoogleOAuth(socialLoginRequest.getAccessToken());

        User user = userService.getByEmail(response.getEmail());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), socialLoginRequest.getStudioId());
        Studio studio = studioService.getById(socialLoginRequest.getStudioId());

        return LoginResponse.of(jwtToken, user, studio);
    }

    @Override
    public LoginResponse loginWithFacebook(SocialLoginRequest socialLoginRequest) {
        FacebookOAuthResponse response = oauthService.requestFacebookOAuth(socialLoginRequest.getAccessToken());

        User user = userService.getByEmail(response.getEmail());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), socialLoginRequest.getStudioId());
        Studio studio = studioService.getById(socialLoginRequest.getStudioId());

        return LoginResponse.of(jwtToken, user, studio);
    }

    private void validatePassword(String rawPassword, String encryptedPassword) {
        boolean isSamePassword = cryptoService.compare(rawPassword, encryptedPassword);
        if (!isSamePassword) {
            throw new YogurtWrongPasswordException("잘못된 비밀번호입니다.");
        }
    }

    @Transactional
    public User signupWithEmail(EmailSignupRequest emailSignupRequest) {
        this.validateEmailForSignup(emailSignupRequest.getEmail());
        verificationService.verifyEmail(emailSignupRequest.getEmail(), emailSignupRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.SIGNUP.name());

        String encryptedPassword = cryptoService.encode(emailSignupRequest.getPassword());
        User user = emailSignupRequest.toEntity(encryptedPassword);

        User savedUser = userRepository.save(user);
        authMailService.sendMailForSignup(savedUser);
        return savedUser;
    }

    public void validateEmailForSignup(String email) {
        boolean existsEmail = userService.existsByEmail(email);
        if (existsEmail) {
            throw new YogurtAlreadyDataUseException("이미 사용중인 이메일입니다.");
        }
    }

    @Transactional
    public User signupWithGoogle(SocialSignupRequest socialSignupRequest) {
        GoogleOAuthResponse response = oauthService.requestGoogleOAuth(socialSignupRequest.getAccessToken());
        validateEmailForSignup(response.getEmail());

        User user = response.toEntity();

        User savedUser = userRepository.save(user);
        authMailService.sendMailForSignup(savedUser);
        return savedUser;
    }


    @Transactional
    public User signupWithFacebook(SocialSignupRequest socialSignupRequest) {
        FacebookOAuthResponse response = oauthService.requestFacebookOAuth(socialSignupRequest.getAccessToken());
        validateEmailForSignup(response.getEmail());

        User user = response.toEntity();

        User savedUser = userRepository.save(user);
        authMailService.sendMailForSignup(savedUser);
        return savedUser;
    }

//    @Transactional
//    public User signupWithApple(AppleSignupRequest appleSignupRequest) {
//        AppleSignupRequest response = oauthService.requestGoogleOAuth(appleSignupRequest.getAccessToken()))
//        validateEmailForSignup(response.getEmail());
//
//        User user = response.toEntity();
//
//        User savedUser = userRepository.save(user);
//        authMailService.sendMailForSignup(savedUser);
//        return savedUser;
//    }

    @Transactional
    public void findPassword(FindPasswordRequest findPasswordRequest) {
        String email = findPasswordRequest.getEmail();
        String verificationCode = findPasswordRequest.getVerificationCode();
        String password = findPasswordRequest.getPassword();

        verificationService.verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.name());

        User user = userService.getByEmail(email);
        userService.changePassword(user.getId(), password);
    }

    @Transactional
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
