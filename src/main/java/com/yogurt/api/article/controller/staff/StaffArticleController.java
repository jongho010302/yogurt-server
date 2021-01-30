package com.yogurt.api.article.controller.staff;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.article.dto.SaveArticleRequest;
import com.yogurt.api.article.dto.UpdateArticleRequest;
import com.yogurt.api.article.service.staff.StaffArticleService;
import com.yogurt.api.auth.domain.AuthContext;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.api.user.domain.User;
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
@RequestMapping("/staff/articles")
public class StaffArticleController {

    private final StaffArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal AuthContext authContext,
                                              Pageable pageable,
                                              @RequestParam(required = false) Boolean isDeleted) {
        List<Article> articleList = service.getByFilter(pageable, authContext.getUser().getId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal AuthContext authContext, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, authContext.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@AuthenticationPrincipal AuthContext authContext, @RequestBody @Valid SaveArticleRequest saveArticleRequest) {
        Article article = service.save(saveArticleRequest, authContext.getUser());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 저장되었습니다.", article), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@AuthenticationPrincipal AuthContext authContext,
                                              @PathVariable Long id,
                                              @RequestBody @Valid UpdateArticleRequest updateArticleRequest) {
        Article article = service.updateByIdAndUser(id, authContext.getUser(), updateArticleRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@AuthenticationPrincipal AuthContext authContext, @PathVariable Long id) {
        service.deleteByIdAndStudioId(id, authContext.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 삭제되었습니다."), HttpStatus.OK);
    }
}
