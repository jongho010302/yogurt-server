package com.yogurt.api.ticket.infra;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.base.util.StringUtils;
import com.yogurt.generic.lesson.domain.ClassType;
import com.yogurt.api.ticket.domain.Ticket;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.api.ticket.domain.QTicket.ticket;

@Repository
public class TicketRepositoryImpl extends QuerydslRepositorySupport implements TicketRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TicketRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Ticket.class);
        this.queryFactory = queryFactory;
    }

    public List<Ticket> getAllWithFilter(Boolean isSelling, String classType, Pageable pageable) {
        JPAQuery<Ticket> query = queryFactory
                .selectFrom(ticket)
                .where(eqIsSelling(isSelling), eqClassType(classType));

        QueryResults<Ticket> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Ticket> ticketList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return ticketList;
    }

    private BooleanExpression eqIsSelling(Boolean isSelling) {
        if (StringUtils.isEmpty(isSelling)) {
            return null;
        }
        return ticket.isSelling.eq(isSelling);
    }

    private BooleanExpression eqClassType(String classType) {
        if (StringUtils.isEmpty(classType)) {
            return null;
        }
        return ticket.classType.eq(ClassType.of(classType));
    }

}
