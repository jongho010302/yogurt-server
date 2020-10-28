package com.yogurt.member.domain;

import com.yogurt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getByUser(User user);
}
