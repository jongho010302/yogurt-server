package com.yogurt.domain.lecture.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.lecture.exception.BookingCancelTimeExceedException;
import com.yogurt.domain.lecture.exception.BookingTimeExceedException;
import com.yogurt.util.DateUtils;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureItem extends BaseEntity {

    @Column(nullable = false)
    private Long studioId;

    @JsonManagedReference
    @ManyToOne
    private Lecture lecture;

    @Column(nullable = false)
    private Long staffId;

    // 수업명
    @Column(nullable = false)
    private String title;

    // 수업 소개
    @Column(columnDefinition = "TEXT")
    private String description;

    // 최대 수강 인원
    @Column(nullable = false)
    private int maxTrainee;

    // 최소 수강 인원
    @Column(nullable = false)
    private int minTrainee;

    // 수업 시작 시간 (yyyy-mm-dd hh:mm:ss)
    @Column(length = 19, nullable = false)
    private String startAt;

    // 수업 종료 시간 (yyyy-mm-dd hh:mm:ss)
    @Column(length = 19, nullable = false)
    private String endAt;

    // 예약 종료 시간 (yyyy-mm-dd hh:mm:ss)
    @Column(length = 19, nullable = false)
    private String bookingEndAt;

    // 예약 취소 종료 시간 (yyyy-mm-dd hh:mm:ss)
    @Column(length = 19, nullable = false)
    private String bookingCancelEndAt;

    // 당일 예약 변경 종료 시간 (yyyy-mm-dd hh:mm:ss)
    @Column(length = 19, nullable = false)
    private String bookingChangeEndAt;

    public void validateExceedLectureBookingTime() {
        if (this.isExceedLectureBookingTime()) {
            throw new BookingTimeExceedException();
        }
    }

    private boolean isExceedLectureBookingTime() {
        Calendar bookingEndCal = DateUtils.getCalendar(this.getBookingEndAt());
        Calendar nowCal = DateUtils.getCalendar();
        return nowCal.after(bookingEndCal);
    }

    public void validateExceedLectureCancelTime() {
        if (this.isExceedLectureCancelTime()) {
            throw new BookingCancelTimeExceedException();
        }
    }

    private boolean isExceedLectureCancelTime() {
        Calendar bookingCancelEndCal = DateUtils.getCalendar(this.getBookingCancelEndAt());
        Calendar nowCal = DateUtils.getCalendar();
        return nowCal.after(bookingCancelEndCal);
    }

}
