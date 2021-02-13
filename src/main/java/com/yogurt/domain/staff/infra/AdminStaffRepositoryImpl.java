package com.yogurt.domain.staff.infra;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.base.util.StringUtils;
import com.yogurt.domain.staff.domain.Staff;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.domain.staff.domain.QStaff.staff;

@Repository
public class AdminStaffRepositoryImpl extends QuerydslRepositorySupport implements AdminStaffRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminStaffRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Staff.class);
        this.queryFactory = queryFactory;
    }

    public List<Staff> getAllWithFilter(Pageable pageable, Boolean isDeleted) {
        JPAQuery<Staff> query = queryFactory
                .selectFrom(staff)
                .where(eqIsDeleted(isDeleted));

        QueryResults<Staff> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Staff> staffList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return staffList;
    }

    private BooleanExpression eqIsDeleted(Boolean isDeleted) {
        if (StringUtils.isEmpty(isDeleted)) {
            return null;
        }
        return staff.isDeleted.eq(isDeleted);
    }
}
