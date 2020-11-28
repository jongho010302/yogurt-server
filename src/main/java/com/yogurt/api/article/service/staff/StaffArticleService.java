package com.yogurt.api.article.service.staff;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.article.dto.SaveArticleRequest;
import com.yogurt.api.article.dto.UpdateArticleRequest;
import com.yogurt.api.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffArticleService {

    List<Article> getByFilter(Pageable pageable, Long studioId);

    Article getByIdAndStudioId(Long id, Long studioId);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    Article updateByIdAndUser(Long id, User user,UpdateArticleRequest updateArticleRequest);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
