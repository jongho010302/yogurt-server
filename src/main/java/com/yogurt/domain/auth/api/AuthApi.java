package com.yogurt.domain.auth.api;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.auth.dto.request.*;
import com.yogurt.domain.auth.service.AuthService;
import com.yogurt.domain.auth.service.VerificationService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.domain.user.domain.User;
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
public class AuthApi {

    private final AuthService authService;

    private final VerificationService verificationService;

    /**
     * 로그인: 이메일
     */
    @PostMapping("/tokens/email")
    public ResponseEntity<ApiResponse> loginWithEmail(@RequestBody @Valid EmailLoginRequest emailLoginRequest) {
        LoginResponse loginResponse = authService.loginWithEmail(emailLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    /**
     * 로그인: 구글
     */
    @PostMapping("/tokens/google")
    public ResponseEntity<ApiResponse> loginWithGoogle(@RequestBody @Valid SocialLoginRequest socialLoginRequest) {
        LoginResponse loginResponse = authService.loginWithGoogle(socialLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    /**
     * 로그인: 페이스북
     */
    @PostMapping("/tokens/facebook")
    public ResponseEntity<ApiResponse> loginWithFacebook(@RequestBody @Valid SocialLoginRequest socialLoginRequest) {
        LoginResponse loginResponse = authService.loginWithFacebook(socialLoginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    /**
     * 회원가입: 이메일
     */
    @PostMapping("/users/email")
    public ResponseEntity<ApiResponse> signupWithEmail(@RequestBody @Valid EmailSignupRequest emailSignupRequest) {
        User user = authService.signupWithEmail(emailSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    /**
     * 회원가입: 구글
     */
    @PostMapping("/users/google")
    public ResponseEntity<ApiResponse> signupWithGoogle(@RequestBody @Valid SocialSignupRequest socialSignupRequest) {
        User user = authService.signupWithGoogle(socialSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    /**
     * 회원가입: 페이스북
     */
    @PostMapping("/users/facebook")
    public ResponseEntity<ApiResponse>
    signupWithFacebook(@RequestBody @Valid SocialSignupRequest socialSignupRequest) {
        User user = authService.signupWithFacebook(socialSignupRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    /**
     * 로그아웃
     */
    @DeleteMapping("/tokens")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        authService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그아웃되었습니다."), HttpStatus.OK);
    }

    /**
     * 계정 삭제
     */
    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse> deleteAccount(@AuthenticationPrincipal AuthContext authContext) {
        authService.deleteUser(authContext.getUser().getId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원 탈퇴되었습니다."), HttpStatus.CREATED);
    }

    /**
     * 비밀번호 찾기
     */
    @PutMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        authService.findPassword(findPasswordRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다."), HttpStatus.OK);
    }


    /**
     * 인증코드 전송: 회원가입
     */
    @PostMapping("/verifications/signup/send")
    public ResponseEntity<ApiResponse> sendSignupCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendSignupCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    /**
     * 인증코드 인증: 회원가입
     */
    @PostMapping("/verifications/signup/verify")
    public ResponseEntity<ApiResponse> verifySignupCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifySignupCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    /**
     * 인증코드 전송: 비밀번호 찾기
     */
    @PostMapping("/verifications/find-password/send")
    public ResponseEntity<ApiResponse> sendFindPasswordCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendFindPasswordCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    /**
     * 인증코드 인증: 비밀번호 찾기
     */
    @PostMapping("/verifications/find-password/verify")
    public ResponseEntity<ApiResponse> verifyFindPasswordCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyFindPasswordCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    /**
     * 인증코드 전송: 이메일 변경
     */
    @PostMapping("/verifications/change-email/send")
    public ResponseEntity<ApiResponse> sendChangeEmailCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendChangeEmailCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    /**
     * 인증코드 인증: 이메일 변경
     */
    @PostMapping("/verifications/change-email/verify")
    public ResponseEntity<ApiResponse> verifyChangeEmailCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyChangeEmailCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }
}