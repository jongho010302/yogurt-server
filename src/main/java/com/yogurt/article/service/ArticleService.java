package com.yogurt.article.service;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Article getByIdAndStudioId(Long id, Long studioId);

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
