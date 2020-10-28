package com.yogurt.lecture.service;

import com.yogurt.lecture.domain.Lecture;
import com.yogurt.lecture.domain.LectureBooking;
import com.yogurt.lecture.domain.LectureItem;
import com.yogurt.lecture.dto.SaveLecturesRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureService {

    List<LectureItem> getAllWithFilter(Long studioId, Pageable pageable, String startAt, String endAt, String weekDay, Long staffId, String classType);

    List<LectureBooking> getLectureBookingList(Long userId, Long memberTicketId);

    Lecture save(SaveLecturesRequest saveLecturesRequest);

    LectureBooking book(Long userId, Long lectureItemId, Long memberTicketId);

    LectureBooking cancel(Long userId, Long lectureBookingId);

    boolean existsLectureItemById(Long id);

    boolean existsLectureBookingById(Long id);
}
