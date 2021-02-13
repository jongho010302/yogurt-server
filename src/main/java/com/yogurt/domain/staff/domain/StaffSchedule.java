package com.yogurt.domain.staff.domain;

import com.yogurt.domain.base.entity.BaseEntity;
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
