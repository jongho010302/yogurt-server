package com.yogurt.domain.article.service.common;

import com.yogurt.domain.article.domain.Article;

public interface CommonArticleService {

    Article getById(Long id);

    Article create(Article article);

    boolean existsById(Long id);

    void deleteById(Long id);
}
