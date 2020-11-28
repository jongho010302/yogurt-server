package com.yogurt.api.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArticleRequest {

    private String title;

    private String content;
}
