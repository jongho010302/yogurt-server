package com.yogurt.domain.lecture.infra.admin;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.domain.base.model.ClassType;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.admin.LecturesResponse;
import com.yogurt.util.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.domain.lecture.domain.QLecture.lecture;
import static com.yogurt.domain.lecture.domain.QLectureItem.lectureItem;
import static com.yogurt.domain.staff.domain.QStaff.staff;

@Repository
public class ALectureItemRepoImpl extends QuerydslRepositorySupport implements ALectureItemRepoCustom {

    private final JPAQueryFactory queryFactory;

    public ALectureItemRepoImpl(JPAQueryFactory queryFactory) {
        super(LectureItem.class);
        this.queryFactory = queryFactory;
    }

    public List<LecturesResponse> getAllWithFilter(Pageable pageable, Long studioId, String startAt, String endAt, String weekDay, Long staffId, String classType) {
        JPAQuery<LecturesResponse> query = queryFactory
                .select(Projections.constructor(LecturesResponse.class,
                        lectureItem.title,
                        lecture.classType,
                        lectureItem.startAt,
                        lectureItem.endAt,
                        lectureItem.maxTrainee,
                        lectureItem.minTrainee,
                        lectureItem.bookingEndAt,
                        lectureItem.bookingCancelEndAt,
                        lectureItem.bookingChangeEndAt,
                        staff.name
                ))
                .from(lectureItem)
                .where(lectureItem.startAt.between(startAt, endAt),
                        lectureItem.studioId.eq(studioId),
                        eqStaffId(staffId),
                        eqClassType(classType))
                .where(weekDayBuilder(weekDay))
                .join(lecture).on(lectureItem.lecture.eq(lecture))
                .join(staff).on(lectureItem.staffId.eq(staff.id));

        QueryResults<LecturesResponse> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<LecturesResponse> lectureItemList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return lectureItemList;
    }

    private BooleanExpression eqStaffId(Long staffId) {
        if (StringUtils.isEmpty(staffId)) {
            return null;
        }
        return lectureItem.staffId.eq(staffId);
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
