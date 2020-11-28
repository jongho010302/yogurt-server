package com.yogurt.api.staff.infra;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.base.util.StringUtils;
import com.yogurt.api.staff.domain.Staff;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.api.staff.domain.QStaff.staff;

@Repository
public class StaffRepositoryImpl extends QuerydslRepositorySupport implements StaffRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StaffRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Staff.class);
        this.queryFactory = queryFactory;
    }

    public List<Staff> getAllWithFilter(Pageable pageable, Boolean isDisabled) {
        JPAQuery<Staff> query = queryFactory
                .selectFrom(staff);

        QueryResults<Staff> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Staff> staffList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return staffList;
    }

    private BooleanExpression eqIsDisabled(Boolean isDisabled) {
        if (StringUtils.isEmpty(isDisabled)) {
            return null;
        }
        return staff.isDisabled.eq(isDisabled);
    }
}
