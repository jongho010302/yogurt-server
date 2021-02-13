package com.yogurt.domain.lecture.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yogurt.domain.ticket.domain.UserTicket;
import com.yogurt.base.util.DateUtils;
import com.yogurt.generic.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureBooking extends BaseEntity {

    @JsonManagedReference
    @ManyToOne
    private UserTicket userTicket;

    @ManyToOne
    private LectureItem lectureItem;

    @Column(nullable = false)
    private Boolean isAttended;

    @Column
    private Date attendedAt;

    @Column(nullable = false)
    private Boolean isCanceled;

    @Column
    private Date canceledAt;

    public void canceled() {
        Date currentDate = DateUtils.getCurrentDate();
        setCanceledAt(currentDate);
        setIsCanceled(true);
    }

    public void attended() {
        Date currentDate = DateUtils.getCurrentDate();
        setAttendedAt(currentDate);
        setIsAttended(true);
    }
}
