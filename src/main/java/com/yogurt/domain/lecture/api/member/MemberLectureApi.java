package com.yogurt.domain.lecture.api.member;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.LectureBookingRequest;
import com.yogurt.domain.lecture.service.member.MemberLectureService;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.global.common.response.Meta;
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
@RequestMapping("/member/lectures")
public class MemberLectureApi {

    private final MemberLectureService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllWithFilter(@AuthenticationPrincipal AuthContext authContext,
                                                        Pageable pageable,
                                                        @RequestParam(value = "start_at") String startAt,
                                                        @RequestParam(value = "end_at") String endAt,
                                                        @RequestParam(value = "week_day", required = false) String weekDay,
                                                        @RequestParam(value = "staff_id", required = false) Long staffId,
                                                        @RequestParam(value = "class_type", required = false) String classType) {
        List<LectureItem> lectureItemList = service.getAllWithFilter(authContext.getStudioId(), pageable, startAt, endAt, weekDay, staffId, classType);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업 리스트입니다.", lectureItemList, Meta.of(pageable, lectureItemList.size())), HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse> getLectureBookingList(@AuthenticationPrincipal AuthContext authContext,
                                              @RequestParam(required = false) Long userTicketId) {
        List<LectureBooking> lectureBookingList = service.getBookingList(authContext.getUser(), userTicketId);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약 내역입니다.", lectureBookingList), HttpStatus.OK);
    }

    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse> bookLecture(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Valid LectureBookingRequest lectureBookingRequest) {
        LectureBooking lectureBooking = service.book(authContext.getUser().getId(), lectureBookingRequest.getLectureItemId(), lectureBookingRequest.getUserTicketId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 완료 되었습니다.", lectureBooking), HttpStatus.OK);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<ApiResponse> cancelLecture(@AuthenticationPrincipal AuthContext authContext, @PathVariable Long id) {
        service.cancel(authContext.getUser().getId(), id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("예약이 취소되었습니다."), HttpStatus.OK);
    }
}
