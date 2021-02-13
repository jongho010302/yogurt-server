package com.yogurt.domain.lecture.infra.common;

import com.yogurt.domain.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonLectureItemRepository extends JpaRepository<LectureItem, Long> {
}
