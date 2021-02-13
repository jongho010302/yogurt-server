package com.yogurt.domain.user.api.base;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.common.*;
import com.yogurt.domain.user.service.base.BaseUserService;
import com.yogurt.domain.user.service.common.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class BaseUserApi {

    private final BaseUserService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> checkUser(@AuthenticationPrincipal AuthContext authContext) {
        CheckResponse checkResponse = service.checkUser(authContext);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저 체크 성공.", checkResponse), HttpStatus.OK);
    }

    @PutMapping("/name")
    public ResponseEntity<ApiResponse> changeName(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Validated ChangeNameRequest changeNameRequest) {
        User changedUser = service.changeName(authContext.getUser().getId(), changeNameRequest.getName());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이름이 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/phone")
    public ResponseEntity<ApiResponse> changePhone(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Validated ChangePhoneRequest changePhoneRequest) {
        User changedUser = service.changePhone(authContext.getUser().getId(), changePhoneRequest.getPhone());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("전화번호가 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> changeProfileUrl(@AuthenticationPrincipal AuthContext authContext, @RequestParam("profile") MultipartFile multipartFile) {
        User changedUser = service.changeProfile(authContext.getUser().getId(), multipartFile);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("프로필이 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Validated ChangePasswordRequest changePasswordRequest, HttpServletRequest request) {
        User changedUser = service.changePassword(authContext.getUser().getId(), changePasswordRequest.getPassword());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/email")
    public ResponseEntity<ApiResponse> changeEmail(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Validated ChangeEmailRequest changeEmailRequest, HttpServletRequest request) {
        service.changeEmail(authContext.getUser().getId(), changeEmailRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일이 변경되었습니다."), HttpStatus.OK);
    }
}