package com.yogurt.api.lecture.dto;

import com.yogurt.generic.lesson.domain.ClassType;
import com.yogurt.api.lecture.domain.Lecture;
import com.yogurt.api.lecture.domain.LectureItem;
import com.yogurt.api.staff.domain.Staff;
import com.yogurt.validation.annotation.*;
import com.yogurt.validation.enums.TimeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

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

    @NotNull(message = "월요일 수업 여부는 필수 값입니다.")
    private Boolean hasMonClass;

    @TimeValid(message = "월요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "월요일 수업 시작 시간은 필수 값입니다.")
    private String monClassStartTime;

    @TimeValid(message = "월요일 수업 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "월요일 수업 종료 시간은 필수 값입니다.")
    private String monClassEndTime;

    @NotNull(message = "화요일 수업 여부는 필수 값입니다.")
    private Boolean hasTueClass;

    @TimeValid(message = "화요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "화요일 수업 여부는 필수 값입니다.")
    private String tueClassStartTime;

    @TimeValid(message = "화요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "화요일 수업 여부는 필수 값입니다.")
    private String tueClassEndTime;

    @NotNull(message = "수요일 수업 여부는 필수 값입니다.")
    private Boolean hasWedClass;

    @TimeValid(message = "수요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "수요일 수업 시작 시간은 필수 값입니다.")
    private String wedClassStartTime;

    @TimeValid(message = "수요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "수요일 수업 종료 시간 필수 값입니다.")
    private String wedClassEndTime;

    @NotNull(message = "목요일 수업 여부는 필수 값입니다.")
    private Boolean hasThuClass;

    @TimeValid(message = "목요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "목요일 수업 시작 시간은 필수 값입니다.")
    private String thuClassStartTime;

    @TimeValid(message = "목요일 수업 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "목요일 수업 종료 시간은 필수 값입니다.")
    private String thuClassEndTime;

    @NotNull(message = "금요일 수업 여부는 필수 값입니다.")
    private Boolean hasFriClass;

    @TimeValid(message = "금요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "금요일 수업 시작 시간은 필수 값입니다.")
    private String friClassStartTime;

    @TimeValid(message = "금요일 수업 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "금요일 수업 종료 시간은 필수 값입니다.")
    private String friClassEndTime;

    @NotNull(message = "토요일 수업 여부는 필수 값입니다.")
    private Boolean hasSatClass;

    @TimeValid(message = "토요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "토요일 수업 시작 시간은 필수 값입니다.")
    private String satClassStartTime;

    @TimeValid(message = "토요일 수업 종료 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "토요일 수업 종료 시간은 필수 값입니다.")
    private String satClassEndTime;

    @NotNull(message = "일요일 수업 여부는 필수 값입니다.")
    private Boolean hasSunClass;

    @TimeValid(message = "일요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "일요일 수업 시작 시간은 필수 값입니다.")
    private String sunClassStartTime;

    @TimeValid(message = "일요일 수업 시작 시간의 형식을 맞춰주세요.", time = TimeEnum.TIME_48)
    @NotEmpty(message = "일요일 수업 종료 시간은 필수 값입니다.")
    private String sunClassEndTime;

    public Lecture toLectureEntity() {
        Staff staff = new Staff();
        staff.setId(staffId);

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
                .staff(staff)
                .title(title)
                .description(description)
                .maxTrainee(maxTrainee)
                .minTrainee(minTrainee)
                .build();
    }
}
