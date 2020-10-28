package com.yogurt.lecture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.ticket.domain.MemberTicket;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureBooking extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private MemberTicket memberTicket;

    @ManyToOne
    private LectureItem lectureItem;

    @Column(nullable = false)
    private Boolean isCanceled;

    @Column(nullable = false)
    private Boolean isAttended;

    public void canceled() {
        this.isCanceled = true;
    }

    public void attended() {
        this.isAttended = true;
    }
}
