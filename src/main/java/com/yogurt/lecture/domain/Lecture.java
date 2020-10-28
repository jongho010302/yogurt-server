package com.yogurt.lecture.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.generic.lesson.domain.ClassType;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture extends BaseEntity {

    @Builder.Default
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<LectureItem> lectureItems = new ArrayList<>();

    private Long studioId;

    private Long staffId;

    private ClassType classType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int maxTrainee;

    // 최소 수강 인원
    @Column(nullable = false)
    private int minTrainee;

    @Column(length = 10, nullable = false)
    private String startDate;

    @Column(length = 10, nullable = false)
    private String endDate;

    // 예약 종료 시간 (hh:mm)
    @Column(length = 5, nullable = false)
    private String bookingEndTime;

    // 예약 취소 종료 시간 (hh:mm)
    @Column(length = 5, nullable = false)
    private String bookingCancelEndTime;

    // 예약 변경 종료 시간 (hh:mm)
    @Column(length = 5, nullable = false)
    private String bookingChangeEndTime;

    public String getClassType() {
        return classType.getClassType();
    }
}
