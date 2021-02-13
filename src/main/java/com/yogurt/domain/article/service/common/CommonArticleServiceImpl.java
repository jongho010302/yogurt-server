package com.yogurt.domain.article.service.common;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.exception.ArticleNotFoundException;
import com.yogurt.domain.article.infra.admin.AdminArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommonArticleServiceImpl implements CommonArticleService {

    private final AdminArticleRepository repository;

    @Transactional
    public Article getById(Long id) {
        Article article = repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
        return article;
    }

    @Transactional
    public Article create(Article article) {
        return repository.save(article);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
