package com.yogurt.api.lecture.infra;

import com.yogurt.api.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureItemRepository extends JpaRepository<LectureItem, Long>, LectureRepositoryCustom {
}
