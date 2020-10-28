package com.yogurt.lecture.controller.common;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
import com.yogurt.lecture.domain.LectureItem;
import com.yogurt.lecture.service.LectureService;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllWithFilter(@AuthenticationPrincipal User user,
                                                        Pageable pageable,
                                                        @RequestParam(value = "start_at") String startAt,
                                                        @RequestParam(value = "end_at") String endAt,
                                                        @RequestParam(value = "week_day", required = false) String weekDay,
                                                        @RequestParam(value = "staff_id", required = false) Long staffId,
                                                        @RequestParam(value = "class_type", required = false) String classType) {
        List<LectureItem> lectureItemList = service.getAllWithFilter(user.getStudioId(), pageable, startAt, endAt, weekDay, staffId, classType);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업 리스트입니다.", lectureItemList, Meta.of(pageable, lectureItemList.size())), HttpStatus.OK);
    }

}
