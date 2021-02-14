package com.yogurt.domain.lecture.dto.admin;

import com.querydsl.core.annotations.QueryProjection;
import com.yogurt.domain.base.model.ClassType;
import com.yogurt.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class LecturesResponse {

    private String title;

    private ClassType classType;

    private String lectureAt;

    private String entry;

    private String bookingEndAt;

    private String bookingCancelEndAt;

    private String bookingChangeEndAt;

    private String staffName;

    public String getClassType() {
        return classType.getClassType();
    }

    @QueryProjection
    public LecturesResponse(String title, ClassType classType, String startAt, String endAt,
                            int maxTrainee, int minTrainee, String bookingEndAt, String bookingCancelEndAt,
                            String bookingChangeEndAt, String staffName) {

        this.title = title;
        this.classType = classType;
        this.lectureAt = DateUtils.getLectureAt(startAt, endAt);
        this.entry = minTrainee + "/" + maxTrainee;
        this.bookingEndAt = bookingEndAt;
        this.bookingEndAt = bookingEndAt;
        this.bookingCancelEndAt = bookingCancelEndAt;
        this.bookingChangeEndAt = bookingChangeEndAt;
        this.staffName = staffName;
    }
}
