package com.yogurt.article.dto;

import com.yogurt.validation.annotation.ArticleValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteArticleRequest {

    @ArticleValid
    @NotNull(message = "게시글은 필수 값입니다.")
    private Long articleId;

}
