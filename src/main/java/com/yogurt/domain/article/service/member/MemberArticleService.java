package com.yogurt.domain.article.service.member;

import com.yogurt.domain.article.domain.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberArticleService {

    Article getByIdAndStudioId(Long id, Long studioId);

    List<Article> getByFilter(Pageable pageable, Long studioId);
}
