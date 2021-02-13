package com.yogurt.domain.lecture.infra.common;

import com.yogurt.domain.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonLectureRepository extends JpaRepository<Lecture, Long> {
}
