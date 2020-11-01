package com.yogurt.lecture.infra;

import com.yogurt.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureItemRepository extends JpaRepository<LectureItem, Long>, LectureRepositoryCustom {
}
