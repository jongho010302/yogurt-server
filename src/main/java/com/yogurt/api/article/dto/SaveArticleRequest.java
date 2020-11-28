package com.yogurt.api.article.dto;

import com.yogurt.api.article.domain.Article;
import com.yogurt.api.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SaveArticleRequest {

    @NotEmpty(message = "수강권은 필수 값입니다.")
    private String title;

    @NotEmpty(message = "설명은 필수 값입니다.")
    private String content;

    public Article toEntity(User user) {
        return Article.builder()
                .studioId(user.getStudioId())
                .user(user)
                .title(title)
                .content(content)
                .viewCount(0)
                .likeCount(0)
                .build();
    }
}
