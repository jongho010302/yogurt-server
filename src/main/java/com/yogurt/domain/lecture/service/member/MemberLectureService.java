package com.yogurt.domain.lecture.service.member;

import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberLectureService {

    List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType);

    List<LectureBooking> getBookingList(User user, Long userTicketId);

    LectureBooking book(Long userId, Long lectureItemId, Long userTicketId);

    LectureBooking cancel(Long userId, Long lectureBookingId);
}
