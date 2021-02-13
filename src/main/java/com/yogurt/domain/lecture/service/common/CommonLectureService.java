package com.yogurt.domain.lecture.service.common;

public interface CommonLectureService {
    boolean existsLectureItemById(Long id);

    boolean existsLectureBookingById(Long id);
}
