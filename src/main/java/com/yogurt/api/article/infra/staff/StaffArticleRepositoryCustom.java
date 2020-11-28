package com.yogurt.api.article.infra.staff;

import com.yogurt.api.article.domain.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffArticleRepositoryCustom {

    List<Article> getByFilter(Pageable pageable, Long studioId);
}
