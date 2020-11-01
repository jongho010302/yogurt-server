package com.yogurt.article.service;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.article.infra.admin.AdminArticleRepository;
import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.exception.YogurtStudioDifferentException;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final AdminArticleRepository repository;

    @Transactional
    public List<Article> getByFilter(Pageable pageable, Long studioId) {
        return repository.getByFilter(pageable, studioId);
    }

    @Transactional
    public Article getByIdAndStudioId(Long id, Long studioId) {
        Article article = repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 게시글입니다."));
        if (!article.getStudioId().equals(studioId)) {
            throw new YogurtStudioDifferentException("회원이 속한 센터의 데이터가 아닙니다.");
        }
        if (article.getIsDeleted()) {
            throw new YogurtAlreadyDataExistsException("삭제된 게사글입니다.");
        }
        return article;
    }

    @Transactional
    public Article save(SaveArticleRequest saveArticleRequest, User user) {
        Article article = saveArticleRequest.toEntity(user.getStudioId(), user.getId());
        return repository.save(article);
    }

    @Transactional
    public void deleteByIdAndStudioId(Long id, Long studioId) {
        Article article = this.getByIdAndStudioId(id, studioId);
        article.setIsDeleted(true);
        repository.save(article);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
