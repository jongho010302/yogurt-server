package com.yogurt.domain.lecture.api.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.dto.SaveLecturesRequest;
import com.yogurt.domain.lecture.service.admin.AdminLectureService;
import com.yogurt.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/lectures")
public class AdminLectureApi {

    private final AdminLectureService service;

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated SaveLecturesRequest saveLecturesRequest) {
        Lecture lecture = service.create(saveLecturesRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업이 저장되었습니다.", lecture), HttpStatus.OK);
    }
}
