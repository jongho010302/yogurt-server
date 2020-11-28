package com.yogurt.api.article.infra.staff;

import com.yogurt.api.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffArticleRepository extends JpaRepository<Article, Long>, StaffArticleRepositoryCustom {
}
