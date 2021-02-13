package com.yogurt.domain.user.api.admin;

import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.dto.admin.ChangeRoleRequest;
import com.yogurt.domain.user.service.admin.AdminUserService;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.global.common.response.Meta;
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
public class AdminUserApi {

    private final AdminUserService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(Pageable pageable) {
        List<User> userList = service.getAllWithFilter(pageable);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저 리스트입니다.", userList, Meta.of(pageable, userList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        User user = service.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저입니다.", user), HttpStatus.OK);
    }

    @PutMapping("/role")
    public ResponseEntity<ApiResponse> changeRole(@RequestBody @Valid ChangeRoleRequest changeRoleRequest) {
        User user = service.changeRole(changeRoleRequest.getUserId(), changeRoleRequest.getRole());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("유저의 권한이 변경되었습니다.", user), HttpStatus.OK);
    }
}
