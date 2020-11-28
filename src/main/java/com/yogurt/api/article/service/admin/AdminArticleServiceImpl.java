package com.yogurt.api.article.service.admin;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.article.dto.SaveArticleRequest;
import com.yogurt.api.article.dto.UpdateArticleRequest;
import com.yogurt.api.article.infra.admin.AdminArticleRepository;
import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.base.exception.YogurtStudioDifferentException;
import com.yogurt.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminArticleServiceImpl implements AdminArticleService {

    private final AdminArticleRepository repository;

    @Transactional
    public List<Article> getByFilter(Pageable pageable, Long studioId, Boolean isDeleted) {
        return repository.getByFilter(pageable, studioId, isDeleted);
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
        Article article = saveArticleRequest.toEntity(user);
        return repository.save(article);
    }

    @Transactional
    public Article updateByIdAndUser(Long id, User user, UpdateArticleRequest updateArticleRequest) {
        Article article = this.getByIdAndStudioId(id, user.getStudioId());
        article.setTitle(updateArticleRequest.getTitle());
        article.setContent(updateArticleRequest.getContent());
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
