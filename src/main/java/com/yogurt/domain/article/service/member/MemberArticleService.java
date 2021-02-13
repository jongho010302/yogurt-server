package com.yogurt.domain.article.service.member;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.dto.admin.response.SaveArticleRequest;
import com.yogurt.domain.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberArticleService {

    Article getByIdAndStudioId(Long id, Long studioId);

    List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted);

    Article save(SaveArticleRequest saveArticleRequest, User user);

    boolean existsById(Long id);

    void deleteByIdAndStudioId(Long id, Long studioId);
}
