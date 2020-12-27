package com.yogurt.api.user.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.dto.admin.ChangeRoleRequest;
import com.yogurt.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(Pageable pageable,
                                              @RequestParam(required = false) Boolean isDeleted) {
        List<User> userList = userService.getAllWithFilter(pageable, isDeleted);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저 리스트입니다.", userList, Meta.of(pageable, userList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저입니다.", user), HttpStatus.OK);
    }

    @PutMapping("/role")
    public ResponseEntity<ApiResponse> changeRole(@RequestBody @Valid ChangeRoleRequest changeRoleRequest) {
        User user = userService.changeRole(changeRoleRequest.getUserId(), changeRoleRequest.getRole());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저의 권한이 변경되었습니다.", user), HttpStatus.OK);
    }
}
