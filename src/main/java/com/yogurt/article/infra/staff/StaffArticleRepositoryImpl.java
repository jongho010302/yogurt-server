package com.yogurt.article.infra.staff;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.article.domain.Article;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.article.domain.QArticle.article;

@Repository
public class StaffArticleRepositoryImpl extends QuerydslRepositorySupport implements StaffArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StaffArticleRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Article.class);
        this.queryFactory = queryFactory;
    }

    public List<Article> getByFilter(Pageable pageable, Long studioId) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article)
                .where(article.studioId.eq(studioId));

        QueryResults<Article> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Article> articleList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return articleList;
    }
}
