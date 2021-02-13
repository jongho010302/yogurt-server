package com.yogurt.domain.lecture.infra.common;

import com.yogurt.domain.lecture.domain.LectureBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonLectureBookingRepository extends JpaRepository<LectureBooking, Long> {
}
