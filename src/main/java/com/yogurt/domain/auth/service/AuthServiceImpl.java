package com.yogurt.domain.auth.service;

import com.yogurt.domain.auth.domain.TokenBlacklist;
import com.yogurt.domain.auth.dto.oauth.FacebookOAuthResponse;
import com.yogurt.domain.auth.dto.oauth.GoogleOAuthResponse;
import com.yogurt.domain.auth.dto.request.*;
import com.yogurt.domain.auth.exception.EmailAlreadyRegisteredException;
import com.yogurt.domain.auth.exception.WrongPasswordException;
import com.yogurt.domain.auth.infra.TokenBlacklistRepository;
import com.yogurt.domain.base.model.VerificationType;
import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.studio.service.admin.AdminStudioService;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.infra.admin.AUserRepo;
import com.yogurt.domain.user.service.base.BaseUserService;
import com.yogurt.domain.user.service.common.CommonUserService;
import com.yogurt.global.crypto.CryptoService;
import com.yogurt.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AdminStudioService adminStudioService;

    private final CommonUserService commonUserService;

    private final BaseUserService baseUserService;

    private final VerificationService verificationService;

    private final CryptoService cryptoService;

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenBlacklistRepository tokenBlacklistRepository;

    private final AUserRepo userRepository;

    private final OAuthService oauthService;

    private final AuthMailService authMailService;

    /**
     * 로그아웃
     * @param request
     */
    @Transactional
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        tokenBlacklistRepository.save(TokenBlacklist.of(token));
    }

    /**
     * 로그인: 이메일
     * @param emailLoginRequest
     * @return
     */
    @Transactional
    public LoginResponse loginWithEmail(EmailLoginRequest emailLoginRequest) {
        User user = commonUserService.getByEmail(emailLoginRequest.getEmail());

        validatePassword(emailLoginRequest.getPassword(), user.getPassword());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), emailLoginRequest.getStudioId());
        Studio studio = adminStudioService.getById(emailLoginRequest.getStudioId());
        return LoginResponse.of(jwtToken, user, studio);
    }

    /**
     * Validation: 비밀번호
     * @param rawPassword
     * @param encryptedPassword
     */
    private void validatePassword(String rawPassword, String encryptedPassword) {
        boolean isSamePassword = cryptoService.compare(rawPassword, encryptedPassword);
        if (!isSamePassword) {
            throw new WrongPasswordException();
        }
    }

    /**
     * 로그인: 구글
     * @param socialLoginRequest
     * @return
     */
    @Transactional
    public LoginResponse loginWithGoogle(SocialLoginRequest socialLoginRequest) {
        GoogleOAuthResponse response = oauthService.requestGoogleOAuth(socialLoginRequest.getAccessToken());

        User user = commonUserService.getByEmail(response.getEmail());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), socialLoginRequest.getStudioId());
        Studio studio = adminStudioService.getById(socialLoginRequest.getStudioId());

        return LoginResponse.of(jwtToken, user, studio);
    }

    /**
     * 로그인: 페이스북
     * @param socialLoginRequest
     * @return
     */
    @Transactional
    public LoginResponse loginWithFacebook(SocialLoginRequest socialLoginRequest) {
        FacebookOAuthResponse response = oauthService.requestFacebookOAuth(socialLoginRequest.getAccessToken());

        User user = commonUserService.getByEmail(response.getEmail());

        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString(), socialLoginRequest.getStudioId());
        Studio studio = adminStudioService.getById(socialLoginRequest.getStudioId());

        return LoginResponse.of(jwtToken, user, studio);
    }

    /**
     * 회원가입: 이메일
     * @param emailSignupRequest
     * @return
     */
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

    /**
     * Validation: 이메일
     * @param email
     */
    public void validateEmailForSignup(String email) {
        boolean existsEmail = commonUserService.existsByEmail(email);
        if (existsEmail) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    /**
     * 회원가입: 구글
     * @param socialSignupRequest
     * @return
     */
    @Transactional
    public User signupWithGoogle(SocialSignupRequest socialSignupRequest) {
        GoogleOAuthResponse response = oauthService.requestGoogleOAuth(socialSignupRequest.getAccessToken());
        validateEmailForSignup(response.getEmail());

        User user = response.toEntity();

        User savedUser = userRepository.save(user);
        authMailService.sendMailForSignup(savedUser);
        return savedUser;
    }

    /**
     * 회원가입: 페이스북
     * @param socialSignupRequest
     * @return
     */
    @Transactional
    public User signupWithFacebook(SocialSignupRequest socialSignupRequest) {
        FacebookOAuthResponse response = oauthService.requestFacebookOAuth(socialSignupRequest.getAccessToken());
        validateEmailForSignup(response.getEmail());

        User user = response.toEntity();

        User savedUser = userRepository.save(user);
        authMailService.sendMailForSignup(savedUser);
        return savedUser;
    }

    /**
     * 비밀번호 찾기
     * @param findPasswordRequest
     */
    @Transactional
    public void findPassword(FindPasswordRequest findPasswordRequest) {
        String email = findPasswordRequest.getEmail();
        String verificationCode = findPasswordRequest.getVerificationCode();
        String password = findPasswordRequest.getPassword();

        verificationService.verifyEmail(email, verificationCode, VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.name());

        User user = commonUserService.getByEmail(email);
        baseUserService.changePassword(user.getId(), password);
    }

    @Transactional
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
