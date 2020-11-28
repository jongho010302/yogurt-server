package com.yogurt.api.article.controller.member;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.article.service.ArticleService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member/articles")
public class MemberArticleController {

    private final ArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal User user,
                                              Pageable pageable,
                                              @RequestParam(required = false) Boolean isDeleted) {
        List<Article> articleList = service.getByFilter(pageable, user.getStudioId(), isDeleted);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, user.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }
}
