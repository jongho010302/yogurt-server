package com.yogurt.lecture.infra;

import com.yogurt.lecture.domain.LectureBooking;
import com.yogurt.lecture.domain.LectureItem;
import com.yogurt.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureBookingRepository extends JpaRepository<LectureBooking, Long> {

    List<LectureBooking> findByUserTicket(UserTicket userTicket);

    List<LectureBooking> findByLectureItem(LectureItem lectureItem);
}
