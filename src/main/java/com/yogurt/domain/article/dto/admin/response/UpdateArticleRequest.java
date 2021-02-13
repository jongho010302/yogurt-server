package com.yogurt.domain.article.dto.admin.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateArticleRequest {

    @NotEmpty(message = "센터 id는 필수 값입니다.")
    private long studioId;

    private String title;

    private String content;
}
