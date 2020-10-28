package com.yogurt.lecture.domain;

import com.yogurt.ticket.domain.MemberTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureBookingRepository extends JpaRepository<LectureBooking, Long> {

    List<LectureBooking> findByMemberTicket(MemberTicket memberTicket);

    List<LectureBooking> findByLectureItem(LectureItem lectureItem);
}
