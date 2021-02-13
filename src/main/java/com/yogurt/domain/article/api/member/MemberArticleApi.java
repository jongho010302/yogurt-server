package com.yogurt.domain.article.api.member;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.service.member.MemberArticleService;
import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.global.common.response.Meta;
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
@RequestMapping("/member/articles")
public class MemberArticleApi {

    private final MemberArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal AuthContext authContext, Pageable pageable) {
        List<Article> articleList = service.getByFilter(pageable, authContext.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList, Meta.of(pageable, articleList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal AuthContext authContext, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, authContext.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }
}
