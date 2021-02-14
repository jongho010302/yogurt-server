package com.yogurt.domain.lecture.dto.admin;

import com.yogurt.domain.base.model.ClassType;
import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.staff.domain.Staff;
import com.yogurt.validation.annotation.*;
import com.yogurt.validation.enums.TimeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaveLecturesRequest {

    @StudioValid
    @NotNull(message = "센터는 필수 값입니다.")
    private Long studioId;

    @StaffValid
    @NotNull(message = "스태프는 필수 값입니다.")
    private Long staffId;

    @ClassTypeValid
    @NotEmpty(message = "수업 타입은 필수 값입니다.")
    private String classType;

    @NotEmpty(message = "수업 제목은 필수 값입니다.")
    private String title;

    @NotEmpty(message = "설명은 필수 값입니다.")
    private String description;

    @NotNull(message = "최대 수강 인원는 필수 값입니다.")
    @Range(min = 1, message = "최대 수강 인원는 1보다 작을 수 없습니다.")
    private Integer maxTrainee;

    @NotNull(message = "최소 수강 인원는 필수 값입니다.")
    @Range(min = 1, message = "최소 수강 인원는 1보다 작을 수 없습니다.")
    private Integer minTrainee;

    @DateValid(message = "수업 시작 날짜의 형식을 맞춰주세요.")
    @NotEmpty(message = "수업 시작 날짜는 필수 값입니다.")
    private String startDate;

    @DateValid(message = "수업 종료 날짜의 형식을 맞춰주세요.")
    @NotEmpty(message = "수업 종료 날짜는 필수 값입니다.")
    private String endDate;

    @TimeValid(message = "예약 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "예약 종료 시간은 필수 값입니다.")
    private String bookingEndTime;

    @TimeValid(message = "예약 취소 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "예약 취소 종료 시간은 필수 값입니다.")
    private String bookingCancelEndTime;

    @TimeValid(message = "당일 예약 변경 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "당일 예약 변경 종료 시간은 필수 값입니다.")
    private String bookingChangeEndTime;

    @NotNull(message = "근무일자는 필수 값입니다.")
    private List<LectureSchedule> schedules;

    public Lecture toLectureEntity() {
        return Lecture.builder()
                .studioId(studioId)
                .staffId(staffId)
                .classType(ClassType.of(classType))
                .title(title)
                .description(description)
                .maxTrainee(maxTrainee)
                .minTrainee(minTrainee)
                .bookingEndTime(bookingEndTime)
                .bookingCancelEndTime(bookingCancelEndTime)
                .bookingChangeEndTime(bookingChangeEndTime)
                .startDate(startDate)
                .endDate(endDate)
                .lectureItems(new ArrayList<>())
                .build();
    }

    public LectureItem toLectureItemEntity(Lecture lecture, Staff staff) {
        return LectureItem.builder()
                .studioId(studioId)
                .lecture(lecture)
                .staffId(staffId)
                .title(title)
                .description(description)
                .maxTrainee(maxTrainee)
                .minTrainee(minTrainee)
                .build();
    }
}