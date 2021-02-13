package com.yogurt.domain.staff.infra.admin;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public List<Staff> getAllWithFilter(Pageable pageable) {
        JPAQuery<Staff> query = queryFactory
                .selectFrom(staff);

        QueryResults<Staff> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Staff> staffList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return staffList;
    }
}
