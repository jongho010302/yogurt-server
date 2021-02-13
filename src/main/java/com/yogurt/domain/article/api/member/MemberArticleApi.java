package com.yogurt.domain.article.api.member;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.article.service.member.MemberArticleService;
import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.base.dto.Meta;
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
public class MemberArticleApi {

    private final MemberArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal AuthContext authContext, Pageable pageable) {
        // 회원은 삭제된 게시글을 조회할 수 없다. 그래서 AdminArticleController 와 다르게 isDeleted 를 파라미터로 받지 않고 이곳에서 false 을 넣어준다.
        List<Article> articleList = service.getByFilter(pageable, authContext.getStudioId(), false);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList, Meta.of(pageable, articleList.size())), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal AuthContext authContext, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, authContext.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }
}
