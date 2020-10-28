package com.yogurt.lecture.controller.member;

import com.yogurt.base.dto.ApiResponse;
import com.yogurt.lecture.domain.LectureBooking;
import com.yogurt.lecture.dto.LectureBookingRequest;
import com.yogurt.lecture.dto.LectureCancelRequest;
import com.yogurt.lecture.service.LectureService;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member/lectures")
public class MemberLectureController {

    private final LectureService service;

    @GetMapping("/booking")
    public ResponseEntity<ApiResponse> getLectureBookingList(@AuthenticationPrincipal User user,
                                              @RequestParam Long memberTicketId) {
        List<LectureBooking> lectureBookingList = service.getLectureBookingList(user.getId(), memberTicketId);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약 내역입니다.", lectureBookingList), HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<ApiResponse> book(@AuthenticationPrincipal User user, @RequestBody @Valid LectureBookingRequest lectureBookingRequest) {
        LectureBooking lectureBooking = service.book(user.getId(), lectureBookingRequest.getLectureItemId(), lectureBookingRequest.getMemberTicketId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 완료 되었습니다.", lectureBooking), HttpStatus.OK);
    }

    @PostMapping("/cancellation")
    public ResponseEntity<ApiResponse> cancel(@AuthenticationPrincipal User user, @RequestBody @Valid LectureCancelRequest lectureCancelRequest) {
        LectureBooking lectureBooking = service.cancel(user.getId(), lectureCancelRequest.getLectureBookingId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 취소되었습니다.", lectureBooking), HttpStatus.OK);
    }
}
