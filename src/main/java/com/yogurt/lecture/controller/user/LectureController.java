package com.yogurt.lecture.controller.user;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
import com.yogurt.lecture.domain.LectureBooking;
import com.yogurt.lecture.domain.LectureItem;
import com.yogurt.lecture.dto.LectureBookingRequest;
import com.yogurt.lecture.dto.LectureCancelRequest;
import com.yogurt.lecture.service.LectureService;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/lectures")
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

    @GetMapping("/booking")
    public ResponseEntity<ApiResponse> getLectureBookingList(@AuthenticationPrincipal User user,
                                              @RequestParam Long memberTicketId) {
        List<LectureBooking> lectureBookingList = service.getLectureBookingList(user.getId(), memberTicketId);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약 내역입니다.", lectureBookingList), HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<ApiResponse> book(@AuthenticationPrincipal User user, @RequestBody @Valid LectureBookingRequest lectureBookingRequest) {
        LectureBooking lectureBooking = service.book(user.getId(), lectureBookingRequest.getLectureItemId(), lectureBookingRequest.getUserTicketId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 완료 되었습니다.", lectureBooking), HttpStatus.OK);
    }

    @PostMapping("/cancellation")
    public ResponseEntity<ApiResponse> cancel(@AuthenticationPrincipal User user, @RequestBody @Valid LectureCancelRequest lectureCancelRequest) {
        LectureBooking lectureBooking = service.cancel(user.getId(), lectureCancelRequest.getLectureBookingId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 취소되었습니다.", lectureBooking), HttpStatus.OK);
    }
}
