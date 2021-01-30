package com.yogurt.api.auth.service;

import com.yogurt.api.auth.dto.*;
import com.yogurt.api.user.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    void logout(HttpServletRequest request);

    LoginResponse loginWithEmail(EmailLoginRequest emailLoginRequest);

    LoginResponse loginWithGoogle(SocialLoginRequest socialLoginRequest);

    LoginResponse loginWithFacebook(SocialLoginRequest socialLoginRequest);

//    LoginResponse loginWithApple(AppleLoginRequest appleLoginRequest);

    User signupWithEmail(EmailSignupRequest emailSignupRequest);

    User signupWithGoogle(SocialSignupRequest socialSignupRequest);

    User signupWithFacebook(SocialSignupRequest socialSignupRequest);

//    User signupWithApple(AppleSignupRequest appleSignupRequest);

    void findPassword(FindPasswordRequest findPasswordRequest);

    String getRandomPassword();
}
