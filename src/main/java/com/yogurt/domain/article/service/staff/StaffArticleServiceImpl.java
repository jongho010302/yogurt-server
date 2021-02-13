package com.yogurt.domain.article.service.staff;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.dto.admin.response.SaveArticleRequest;
import com.yogurt.domain.article.dto.admin.response.UpdateArticleRequest;
import com.yogurt.domain.article.exception.ArticleNotFoundException;
import com.yogurt.domain.article.infra.staff.StaffArticleRepository;
import com.yogurt.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StaffArticleServiceImpl implements StaffArticleService {

    private final StaffArticleRepository repository;

    @Transactional
    public List<Article> getByFilter(Pageable pageable, Long studioId) {
        return repository.getByFilter(pageable, studioId);
    }

    @Transactional
    public Article getByIdAndStudioId(Long id, Long studioId) {
        Article article = repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
        return article;
    }

    @Transactional
    public Article save(SaveArticleRequest saveArticleRequest, User user) {
        Article article = saveArticleRequest.toEntity(user);
        return repository.save(article);
    }

    @Transactional
    public Article updateByIdAndUser(Long id, User user, UpdateArticleRequest updateArticleRequest) {
        Article article = this.getByIdAndStudioId(id, updateArticleRequest.getStudioId());
        article.setTitle(updateArticleRequest.getTitle());
        article.setContent(updateArticleRequest.getContent());
        return repository.save(article);
    }

    @Transactional
    public void deleteByIdAndStudioId(Long id, Long studioId) {
        Article article = this.getByIdAndStudioId(id, studioId);
        repository.delete(article);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
