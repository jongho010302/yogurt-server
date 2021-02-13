package com.yogurt.domain.article.api.staff;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.dto.admin.response.SaveArticleRequest;
import com.yogurt.domain.article.dto.admin.response.UpdateArticleRequest;
import com.yogurt.domain.article.service.staff.StaffArticleService;
import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.global.common.response.ApiResponse;
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
public class StaffArticleApi {

    private final StaffArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal AuthContext authContext,
                                              Pageable pageable) {
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
