package com.yogurt.article.service.admin;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.article.dto.UpdateArticleRequest;
import com.yogurt.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleService {

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);

    Article getByIdAndStudioId(Long id, Long studioId);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    Article updateByIdAndUser(Long id, User user, UpdateArticleRequest updateArticleRequest);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
