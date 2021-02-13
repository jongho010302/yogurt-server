package com.yogurt.domain.user.infra;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
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

import static com.yogurt.domain.user.domain.QUser.user;
import static com.yogurt.domain.ticket.domain.QTicket.ticket;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(LectureItem.class);
        this.queryFactory = queryFactory;
    }

    public List<User> getAllWithFilter(Pageable pageable, Boolean isDeleted) {
        JPAQuery<User> query = queryFactory
                .selectFrom(user)
                .where(eqIsDeleted(isDeleted))
                .join(ticket).on(ticket.id.eq(user.id));

        QueryResults<User> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<User> userList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return userList;
    }

    private BooleanExpression eqIsDeleted(Boolean isDeleted) {
        if (isDeleted == null) {
            return null;
        }
        return user.isDeleted.eq(isDeleted);
    }

}
