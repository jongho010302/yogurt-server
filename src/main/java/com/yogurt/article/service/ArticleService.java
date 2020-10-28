package com.yogurt.article.service;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Article getById(Long id);

    List<Article> getArticlesWithFilter(Pageable pageable);

    Article save(User user, SaveArticleRequest saveArticleRequest);

    boolean existsById(Long id);

    void deleteById(Long id);
}
