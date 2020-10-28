package com.yogurt.lecture.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureItemRepository extends JpaRepository<LectureItem, Long>, LectureRepositoryCustom {
}
