package com.yogurt.lecture.controller.admin;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.lecture.domain.Lecture;
import com.yogurt.lecture.dto.SaveLecturesRequest;
import com.yogurt.lecture.service.LectureService;
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
public class AdminLectureController {

    private final LectureService service;

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated SaveLecturesRequest saveLecturesRequest) {
        Lecture lecture = service.save(saveLecturesRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업이 저장되었습니다.", lecture), HttpStatus.OK);
    }

}
