package com.yogurt.domain.lecture.infra.admin;

import com.yogurt.domain.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ALectureRepo extends JpaRepository<Lecture, Long> {
}
