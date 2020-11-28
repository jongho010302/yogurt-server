package com.yogurt.api.lecture.infra;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.lesson.domain.ClassType;
import com.yogurt.api.lecture.domain.LectureItem;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.api.lecture.domain.QLectureItem.lectureItem;

@Repository
public class LectureItemRepositoryImpl extends QuerydslRepositorySupport implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LectureItemRepositoryImpl(JPAQueryFactory queryFactory) {
        super(LectureItem.class);
        this.queryFactory = queryFactory;
    }

    public List<LectureItem> getAllWithFilter(Pageable pageable, Long studioId, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        JPAQuery<LectureItem> query = queryFactory
                .selectFrom(lectureItem)
                .where(lectureItem.startAt.between(startAt, endAt),
                        lectureItem.studioId.eq(studioId),
                        eqStaffId(staffId),
                        eqClassType(classType))
                .where(weekDayBuilder(weekDay));

        QueryResults<LectureItem> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<LectureItem> lectureItemList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return lectureItemList;
    }

    private BooleanExpression eqStaffId(Long staffId) {
        if (StringUtils.isEmpty(staffId)) {
            return null;
        }
        return lectureItem.staff.id.eq(staffId);
    }

    private BooleanExpression eqClassType(String classType) {
        if (StringUtils.isEmpty(classType)) {
            return null;
        }
        return lectureItem.lecture.classType.eq(ClassType.of(classType));
    }

    private BooleanBuilder weekDayBuilder(String weekDay) {
        if (StringUtils.isEmpty(weekDay)) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Arrays.stream(weekDay.split(",")).forEach(day -> {
            booleanBuilder.or(Expressions.numberTemplate(Integer.class, "dayofweek({0})", lectureItem.startAt).eq(Integer.parseInt(day)));
        });
        return booleanBuilder;
    }

}
