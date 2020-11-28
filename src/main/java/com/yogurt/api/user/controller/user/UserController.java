package com.yogurt.api.user.controller.user;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.dto.common.*;
import com.yogurt.api.user.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/check")
    public ResponseEntity<ApiResponse> checkUser(@AuthenticationPrincipal User user) {
        User checkedUser = userService.checkUser(user);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저 체크 성공.", checkedUser), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("로그아웃되었습니다."), HttpStatus.OK);
    }

    @PutMapping("/name")
    public ResponseEntity<ApiResponse> changeName(@AuthenticationPrincipal User user, @RequestBody @Validated ChangeNameRequest changeNameRequest) {
        User changedUser = userService.changeName(user.getId(), changeNameRequest.getName());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이름이 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/phone")
    public ResponseEntity<ApiResponse> changePhone(@AuthenticationPrincipal User user, @RequestBody @Validated ChangePhoneRequest changePhoneRequest) {
        User changedUser = userService.changePhone(user.getId(), changePhoneRequest.getPhone());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("전화번호가 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/username")
    public ResponseEntity<ApiResponse> changeUsername(@AuthenticationPrincipal User user, @RequestBody @Validated ChangeUsernameRequest changeUsernameRequest, HttpServletRequest request) {
        User changedUser = userService.changeUsername(user.getId(), changeUsernameRequest.getUsername());
        userService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("아이디가 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> changeProfileUrl(@AuthenticationPrincipal User user, @RequestParam("profile") MultipartFile multipartFile) {
        User changedUser = userService.changeProfile(user.getId(), multipartFile);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("프로필이 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@AuthenticationPrincipal User user, @RequestBody @Validated ChangePasswordRequest changePasswordRequest, HttpServletRequest request) {
        User changedUser = userService.changePassword(user.getId(), changePasswordRequest.getPassword());
        userService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 변경되었습니다.", changedUser), HttpStatus.OK);
    }

    @PutMapping("/email")
    public ResponseEntity<ApiResponse> changeEmail(@AuthenticationPrincipal User user, @RequestBody @Validated ChangeEmailRequest changeEmailRequest, HttpServletRequest request) {
        userService.changeEmail(user.getId(), changeEmailRequest);
        userService.logout(request);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("이메일이 변경되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/exit")
    public ResponseEntity<ApiResponse> exit(@AuthenticationPrincipal User user, @RequestBody @Validated ExitRequest exitRequest) {
        User changedUser = userService.exit(user.getId(), exitRequest.getExitReason());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원탈퇴 되었습니다.", changedUser), HttpStatus.OK);
    }
}