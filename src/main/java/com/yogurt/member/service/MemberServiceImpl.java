package com.yogurt.member.service;

import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.member.domain.Member;
import com.yogurt.member.domain.MemberRepository;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Transactional
    public Member getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public Member getByUser(User user) {
        return repository.getByUser(user);
    }

    @Transactional
    public Member save(Member member) {
        return repository.save(member);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
