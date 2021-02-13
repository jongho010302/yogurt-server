package com.yogurt.domain.user.infra.admin;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.domain.ticket.domain.QTicket.ticket;
import static com.yogurt.domain.user.domain.QUser.user;

@Repository
public class AdminUserRepositoryImpl extends QuerydslRepositorySupport implements AdminUserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminUserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(LectureItem.class);
        this.queryFactory = queryFactory;
    }

    public List<User> getAllWithFilter(Pageable pageable) {
        JPAQuery<User> query = queryFactory
                .selectFrom(user)
                .join(ticket).on(ticket.id.eq(user.id));

        QueryResults<User> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<User> userList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return userList;
    }
}
