package com.yogurt.member.service;

import com.yogurt.member.domain.Member;
import com.yogurt.user.domain.User;

public interface MemberService {

    Member getById(Long id);

    Member getByUser(User user);

    Member save(Member member);

    boolean existsById(Long id);
}