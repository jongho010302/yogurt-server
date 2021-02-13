package com.yogurt.domain.article.infra.staff;

import com.yogurt.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffArticleRepository extends JpaRepository<Article, Long>, StaffArticleRepositoryCustom {
}
