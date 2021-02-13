package com.yogurt.domain.article.dto.admin.response;

import com.yogurt.domain.article.domain.Article;
import com.yogurt.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SaveArticleRequest {

    @NotEmpty(message = "센터 id는 필수 값입니다.")
    private long studioId;

    @NotEmpty(message = "수강권은 필수 값입니다.")
    private String title;

    @NotEmpty(message = "설명은 필수 값입니다.")
    private String content;

    public Article toEntity(User user) {
        return Article.builder()
                .studioId(studioId)
                .user(user)
                .title(title)
                .content(content)
                .viewCount(0)
                .likeCount(0)
                .build();
    }
}
