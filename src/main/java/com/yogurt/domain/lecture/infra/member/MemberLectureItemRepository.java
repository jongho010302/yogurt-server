package com.yogurt.domain.lecture.infra.member;

import com.yogurt.domain.lecture.domain.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLectureItemRepository extends JpaRepository<LectureItem, Long>, MemberLectureRepositoryCustom {
}
