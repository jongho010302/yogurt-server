package com.yogurt.domain.lecture.infra.member;

import com.yogurt.domain.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MLectureItemRepo extends JpaRepository<LectureItem, Long>, MLectureItemRepoCustom {
}
