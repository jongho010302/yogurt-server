package com.yogurt.api.article.infra.admin;

import com.yogurt.api.article.domain.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleRepositoryCustom {

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);
}
