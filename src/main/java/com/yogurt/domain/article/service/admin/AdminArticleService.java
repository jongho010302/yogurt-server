package com.yogurt.domain.article.service.admin;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.dto.admin.response.SaveArticleRequest;
import com.yogurt.domain.article.dto.admin.response.UpdateArticleRequest;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleService {

    List<Article> getByFilter(Pageable pageable, Long studioId);

    Article getByIdAndStudioId(Long id, Long studioId);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    Article updateByIdAndUser(Long id, User user, UpdateArticleRequest updateArticleRequest);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
