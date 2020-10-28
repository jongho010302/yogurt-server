package com.yogurt.staff.domain;

import com.yogurt.generic.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StaffSchedule extends BaseEntity {

    @Column(nullable = false)
    private int datOfWeek;

    @Column(length = 10, nullable = false)
    private String startTime;

    @Column(length = 10, nullable = false)
    private String endTime;
}
