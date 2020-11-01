package com.yogurt.article.infra.staff;

import com.yogurt.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffArticleRepository extends JpaRepository<Article, Long>, StaffArticleRepositoryCustom {
}
