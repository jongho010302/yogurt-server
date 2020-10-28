package com.yogurt.staff.controller.staff;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.service.StaffService;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;

    @GetMapping("/current")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal User user) {
        Staff member = service.getByUser(user);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("회원이 조회되었습니다.", member), HttpStatus.OK);
    }
}
