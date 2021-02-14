package com.yogurt.domain.lecture.infra.admin;

import com.yogurt.domain.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ALectureItemRepo extends JpaRepository<LectureItem, Long>, ALectureItemRepoCustom {
}
