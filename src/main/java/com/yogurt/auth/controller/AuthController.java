package com.yogurt.auth.controller;

import com.yogurt.auth.dto.*;
import com.yogurt.auth.service.AuthService;
import com.yogurt.auth.service.VerificationService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.member.domain.Member;
import com.yogurt.studio.domain.Studio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final VerificationService verificationService;

    @GetMapping("/studios")
    public ResponseEntity<ApiResponse> getStudioList() {
        List<Studio> studioList = authService.getStudioList();
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("센터 리스트입니다.", studioList), HttpStatus.OK);
    }

    @PostMapping("/verification/send")
    public ResponseEntity<ApiResponse> sendVerificationCode(@RequestBody @Valid SendVerificationCodeRequest sendVerificationCodeRequest) {
        verificationService.sendVerificationCode(sendVerificationCodeRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일로 인증번호를 전송했습니다."), HttpStatus.OK);
    }

    @PostMapping("/verification/verify")
    public ResponseEntity<ApiResponse> verifyVerificationCode(@RequestBody @Valid VerifyVerificationCodeRequest verifyVerificationCodeRequest) {
        verificationService.verifyEmail(verifyVerificationCodeRequest.getEmail(), verifyVerificationCodeRequest.getVerificationCode(), verifyVerificationCodeRequest.getVerificationType());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("인증되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/validate/username")
    public ResponseEntity<ApiResponse> validateUsername(@RequestBody @Valid ValidateUsernameRequest validateUsernameRequest) {
        authService.validateUsername(validateUsernameRequest.getUsername());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("사용 가능한 아이디입니다."), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody @Valid SaveMemberRequest saveMemberRequest) {
        Member member = authService.saveMember(saveMemberRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원가입 되었습니다.", member), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그인되었습니다.", loginResponse), HttpStatus.OK);
    }

    @GetMapping("/find/masking-username")
    public ResponseEntity<ApiResponse> findMaskingUsername(@RequestParam @NotEmpty(message = "이름은 필수 값입니다.") String name) {
        List<String> maskingUsernameList = authService.findMaskingUsername(name);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("아이디 찾기 결과입니다.", maskingUsernameList), HttpStatus.OK);
    }

    @PostMapping("/find/username")
    public ResponseEntity<ApiResponse> findUsername(@RequestBody @Valid FindUsernameRequest findUsernameRequest) {
        authService.findUsername(findUsernameRequest.getEmail());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("해당 이메일로 아이디를 전송했습니다."), HttpStatus.OK);
    }

    @PutMapping("/find/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        authService.findPassword(findPasswordRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다."), HttpStatus.OK);
    }

}