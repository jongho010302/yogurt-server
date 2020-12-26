package com.yogurt.api.auth.controller;

import com.yogurt.api.auth.dto.*;
import com.yogurt.api.auth.service.AuthService;
import com.yogurt.api.auth.service.VerificationService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final VerificationService verificationService;

    @PostMapping("/verification/signup/send")
    public ResponseEntity<ApiResponse> sendSignupCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendSignupCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/signup/verify")
    public ResponseEntity<ApiResponse> verifySignupCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifySignupCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), verifyVerificationCodeRequest.getVerificationType());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/find-password/send")
    public ResponseEntity<ApiResponse> sendFindPasswordCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendFindPasswordCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/find-password/verify")
    public ResponseEntity<ApiResponse> verifyFindPasswordCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyFindPasswordCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), verifyVerificationCodeRequest.getVerificationType());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/change-email/send")
    public ResponseEntity<ApiResponse> sendChangeEmailCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendChangeEmailCode(sendVerificationCodeRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/change-email/verify")
    public ResponseEntity<ApiResponse> verifyChangeEmailCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyChangeEmailCode(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), verifyVerificationCodeRequest.getVerificationType());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse> signup(@RequestBody @Valid SaveUserRequest saveUserRequest) {
        User user = authService.saveUser(saveUserRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        authService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그아웃되었습니다."), HttpStatus.OK);
    }

    @PutMapping("/find/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        authService.findPassword(findPasswordRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다."), HttpStatus.OK);
    }

}