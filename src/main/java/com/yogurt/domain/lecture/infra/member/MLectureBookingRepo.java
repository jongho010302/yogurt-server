package com.yogurt.domain.lecture.infra.member;

import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.ticket.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MLectureBookingRepo extends JpaRepository<LectureBooking, Long> {

    List<LectureBooking> findByUserTicketInAndIsCanceled(List<UserTicket> userTickets, Boolean isCancel);

    List<LectureBooking> findByLectureItem(LectureItem lectureItem);
}
