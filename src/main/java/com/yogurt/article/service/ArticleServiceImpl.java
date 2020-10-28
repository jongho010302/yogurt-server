package com.yogurt.article.service;

import com.yogurt.article.domain.Article;
import com.yogurt.article.domain.ArticleRepository;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.base.exception.YogurtAlreadyDataExistsException;
import com.yogurt.base.exception.YogurtDataNotExistsException;
import com.yogurt.staff.domain.Staff;
import com.yogurt.staff.service.StaffService;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    private final StaffService staffService;

    @Transactional
    public Article getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new YogurtDataNotExistsException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public List<Article> getArticlesWithFilter(Pageable pageable) {
        return repository.getArticlesWithFilter(pageable);
    }

    @Transactional
    public Article save(User user, SaveArticleRequest saveArticleRequest) {
        Staff staff = staffService.getByUser(user);

        Article article = saveArticleRequest.toEntity(user.getStudioId(), staff.getId());
        return repository.save(article);
    }

    @Transactional
    public void deleteById(Long id) {
        Article article = this.getById(id);
        if (article.getIsDeleted()) {
            throw new YogurtAlreadyDataExistsException("이미 삭제된 게사글입니다.");
        }
        article.setIsDeleted(true);
        repository.save(article);
    }

    @Transactional
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
