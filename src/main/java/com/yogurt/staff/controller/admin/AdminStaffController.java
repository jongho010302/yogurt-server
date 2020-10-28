package com.yogurt.staff.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.dto.DeleteStaffRequest;
import com.yogurt.staff.dto.ResetStaffPasswordRequest;
import com.yogurt.staff.dto.SaveStaffRequest;
import com.yogurt.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/staffs")
public class AdminStaffController {

    private final StaffService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(Pageable pageable,
                                              @RequestParam(required = false) Boolean isDisabled) {
        List<Staff> staffList = service.getAllWithFilter(pageable, isDisabled);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("스태프 리스트입니다.", staffList, Meta.of(pageable, staffList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        Staff staff = service.getById(id);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("스태프입니다.", staff), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid SaveStaffRequest saveStaffRequest) {
        Staff staff = service.saveStaff(saveStaffRequest);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("스태프가 저장되었습니다.", staff), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> delete(@RequestBody @Valid DeleteStaffRequest staffSDeleteRequest) {
        service.deactivate(staffSDeleteRequest.getStaffId());

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("스태프가 삭제되었습니다."), HttpStatus.OK);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetStaffPassword(@RequestBody @Valid ResetStaffPasswordRequest resetStaffPasswordRequest) {
        service.resetPassword(resetStaffPasswordRequest.getUserId());

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("비밀번호가 초기화되어 해당 스태프의 이메일로 임시 비밀번호가 전송되었습니다."), HttpStatus.OK);
    }
}
