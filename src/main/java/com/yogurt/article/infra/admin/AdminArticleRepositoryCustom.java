package com.yogurt.article.infra.admin;

import com.yogurt.article.domain.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleRepositoryCustom {

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);
}
