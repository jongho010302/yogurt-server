package com.yogurt.api.article.service;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.article.dto.SaveArticleRequest;
import com.yogurt.api.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Article getByIdAndStudioId(Long id, Long studioId);

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
