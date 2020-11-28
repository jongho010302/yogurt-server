package com.yogurt.api.article.infra.admin;

import com.yogurt.api.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminArticleRepository extends JpaRepository<Article, Long>, AdminArticleRepositoryCustom {
}
