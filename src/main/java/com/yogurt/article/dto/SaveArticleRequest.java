package com.yogurt.article.dto;

import com.yogurt.article.domain.Article;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveArticleRequest {

    @NotEmpty(message = "수강권은 필수 값입니다.")
    private String title;

    @NotEmpty(message = "설명은 필수 값입니다.")
    private String content;

    @NotEmpty(message = "작성자는 필수 값입니다.")
    private String authorName;

    @NotNull(message = "가격은 필수 값입니다.")
    private Integer viewCount;

    @NotNull(message = "가격은 필수 값입니다.")
    private Integer likeCount;

    public Article toEntity(Long studioId, Long staffId) {
        return Article.builder()
                .staffId(staffId)
                .studioId(studioId)
                .title(title)
                .content(content)
                .authorName(authorName)
                .viewCount(0)
                .likeCount(0)
                .build();
    }
}
