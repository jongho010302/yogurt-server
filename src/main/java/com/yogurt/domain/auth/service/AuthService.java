package com.yogurt.domain.auth.service;

import com.yogurt.domain.auth.dto.request.*;
import com.yogurt.domain.user.domain.User;

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

    void deleteUser(long userId);
}
