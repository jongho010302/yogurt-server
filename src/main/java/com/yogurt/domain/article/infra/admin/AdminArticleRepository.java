package com.yogurt.domain.article.infra.admin;

import com.yogurt.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminArticleRepository extends JpaRepository<Article, Long>, AdminArticleRepositoryCustom {
}
