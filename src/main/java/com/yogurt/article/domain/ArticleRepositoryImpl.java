package com.yogurt.article.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.article.domain.QArticle.article;

@Repository
public class ArticleRepositoryImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Article.class);
        this.queryFactory = queryFactory;
    }

    public List<Article> getArticlesWithFilter(Pageable pageable) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article);

        QueryResults<Article> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<Article> articleList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return articleList;
    }

}
