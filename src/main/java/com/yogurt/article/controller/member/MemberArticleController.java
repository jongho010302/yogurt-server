package com.yogurt.article.controller.member;

import com.yogurt.article.domain.Article;
import com.yogurt.article.service.ArticleService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/articles")
public class MemberArticleController {

    private final ArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal User user, Pageable pageable) {
        List<Article> articleList = service.getByFilter(pageable, user.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, user.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }
}
