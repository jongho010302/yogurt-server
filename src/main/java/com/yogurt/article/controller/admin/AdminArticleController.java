package com.yogurt.article.controller.admin;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.DeleteArticleRequest;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.article.service.ArticleService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/articles")
public class AdminArticleController {

    private final ArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(Pageable pageable) {
        List<Article> articleList = service.getArticlesWithFilter(pageable);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        Article article = service.getById(id);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@AuthenticationPrincipal User user, @RequestBody @Valid SaveArticleRequest saveArticleRequest) {
        Article article = service.save(user, saveArticleRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 저장되었습니다.", article), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> delete(@RequestBody @Valid DeleteArticleRequest deleteArticleRequest) {
        service.deleteById(deleteArticleRequest.getArticleId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 삭제되었습니다."), HttpStatus.OK);
    }
}
