package com.yogurt.domain.article.infra.common;

import com.yogurt.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonArticleRepository extends JpaRepository<Article, Long> {
}
