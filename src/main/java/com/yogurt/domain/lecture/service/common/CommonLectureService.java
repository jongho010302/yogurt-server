package com.yogurt.domain.lecture.service.common;

import com.yogurt.domain.lecture.domain.LectureBooking;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonLectureService {
    boolean existsLectureItemById(Long id);

    boolean existsLectureBookingById(Long id);
}
