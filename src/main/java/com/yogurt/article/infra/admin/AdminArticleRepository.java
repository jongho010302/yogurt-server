package com.yogurt.article.infra.admin;

import com.yogurt.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminArticleRepository extends JpaRepository<Article, Long>, AdminArticleRepositoryCustom {
}
