package com.yogurt.domain.article.service.member;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.exception.ArticleNotFoundException;
import com.yogurt.domain.article.infra.admin.AdminArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberArticleServiceImpl implements MemberArticleService {

    private final AdminArticleRepository repository;

    @Transactional
    public List<Article> getByFilter(Pageable pageable, Long studioId) {
        return repository.getByFilter(pageable, studioId);
    }

    @Transactional
    public Article getByIdAndStudioId(Long id, Long studioId) {
        Article article = repository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
        // TODO
        if (!article.getStudioId().equals(studioId)) {
//            throw new YogurtStudioDifferentException("회원이 속한 센터의 데이터가 아닙니다.");
        }
        return article;
    }
}
