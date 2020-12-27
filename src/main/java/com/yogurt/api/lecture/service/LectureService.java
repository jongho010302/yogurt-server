package com.yogurt.api.lecture.service;

import com.yogurt.api.lecture.domain.Lecture;
import com.yogurt.api.lecture.domain.LectureBooking;
import com.yogurt.api.lecture.domain.LectureItem;
import com.yogurt.api.lecture.dto.SaveLecturesRequest;
import com.yogurt.api.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureService {

    List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType);

    List<LectureBooking> getBookingList(User user, Long userTicketId);

    Lecture save(SaveLecturesRequest saveLecturesRequest);

    LectureBooking book(Long userId, Long lectureItemId, Long userTicketId);

    LectureBooking cancel(Long userId, Long lectureBookingId);

    boolean existsLectureItemById(Long id);

    boolean existsLectureBookingById(Long id);
}
