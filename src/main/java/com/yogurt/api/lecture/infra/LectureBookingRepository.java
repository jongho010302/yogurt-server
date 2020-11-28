package com.yogurt.api.lecture.infra;

import com.yogurt.api.lecture.domain.LectureBooking;
import com.yogurt.api.lecture.domain.LectureItem;
import com.yogurt.api.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureBookingRepository extends JpaRepository<LectureBooking, Long> {

    List<LectureBooking> findByUserTicket(UserTicket userTicket);

    List<LectureBooking> findByLectureItem(LectureItem lectureItem);
}
