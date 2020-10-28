package com.yogurt.article.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Article> getArticlesWithFilter(Pageable pageable);
}
