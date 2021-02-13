package com.yogurt.domain.lecture.infra.common;

import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonLectureBookingRepository extends JpaRepository<LectureBooking, Long> {
}
