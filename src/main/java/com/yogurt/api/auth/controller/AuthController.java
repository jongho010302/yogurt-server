package com.yogurt.api.auth.controller;

import com.yogurt.api.auth.domain.AuthContext;
import com.yogurt.api.auth.dto.*;
import com.yogurt.api.auth.service.AuthService;
import com.yogurt.api.auth.service.VerificationService;
import com.yogurt.api.user.dto.common.DeleteUserRequest;
import com.yogurt.api.user.service.UserService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.user.domain.User;
import com.yogurt.generic.user.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final VerificationService verificationService;

    // Verifications

    @PostMapping("/verifications/signup/send")
    public ResponseEntity<ApiResponse> sendSignupCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendSignupCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications/signup/verify")
    public ResponseEntity<ApiResponse> verifySignupCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifySignupCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.SIGNUP.toString());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications/find-password/send")
    public ResponseEntity<ApiResponse> sendFindPasswordCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendFindPasswordCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications/find-password/verify")
    public ResponseEntity<ApiResponse> verifyFindPasswordCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyFindPasswordCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.FIND_PASSWORD.toString());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications/change-email/send")
    public ResponseEntity<ApiResponse> sendChangeEmailCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendChangeEmailCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications/change-email/verify")
    public ResponseEntity<ApiResponse> verifyChangeEmailCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyChangeEmailCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), VerificationType.VERIFICATION_TYPE.CHANGE_EMAIL.toString());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    // Signup

    @PostMapping("/users/email")
    public ResponseEntity<ApiResponse> signupWithEmail(@RequestBody @Valid EmailSignupRequest emailSignupRequest) {
        User user = authService.signupWithEmail(emailSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    @PostMapping("/users/google")
    public ResponseEntity<ApiResponse> signupWithGoogle(@RequestBody @Valid SocialSignupRequest socialSignupRequest) {
        User user = authService.signupWithGoogle(socialSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    @PostMapping("/users/facebook")
    public ResponseEntity<ApiResponse> signupWithFacebook(@RequestBody @Valid SocialSignupRequest socialSignupRequest) {
        User user = authService.signupWithFacebook(socialSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

//    @PostMapping("/users/apple")
//    public ResponseEntity<ApiResponse> signupWithApple(@RequestBody @Valid AppleSignupRequest appleSignupRequest) {
//        User user = authService.signupWithApple(appleSignupRequest);
//        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
//    }

    // Login

    @PostMapping("/tokens/email")
    public ResponseEntity<ApiResponse> loginWithEmail(@RequestBody @Valid EmailLoginRequest emailLoginRequest) {
        LoginResponse loginResponse = authService.loginWithEmail(emailLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    @PostMapping("/tokens/google")
    public ResponseEntity<ApiResponse> loginWithGoogle(@RequestBody @Valid SocialLoginRequest socialLoginRequest) {
        LoginResponse loginResponse = authService.loginWithGoogle(socialLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    @PostMapping("/tokens/facebook")
    public ResponseEntity<ApiResponse> loginWithFacebook(@RequestBody @Valid SocialLoginRequest socialLoginRequest) {
        LoginResponse loginResponse = authService.loginWithFacebook(socialLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

//    @PostMapping("/tokens/apple")
//    public ResponseEntity<ApiResponse> loginWithApple(@RequestBody @Valid AppleLoginRequest appleLoginRequest) {
//        LoginResponse loginResponse = authService.loginWithApple(appleLoginRequest);
//        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
//    }

    // Logout / Delete Account

    @DeleteMapping("/tokens")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        authService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그아웃되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse> deleteAccount(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Valid DeleteUserRequest deleteUserRequest) {
        userService.delete(authContext.getUser().getId(), deleteUserRequest.getDeleteReason());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원 탈퇴되었습니다."), HttpStatus.CREATED);
    }

    // Find Password

    @PutMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        authService.findPassword(findPasswordRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다."), HttpStatus.OK);
    }

}