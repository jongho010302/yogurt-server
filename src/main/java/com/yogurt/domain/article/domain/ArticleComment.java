package com.yogurt.domain.article.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.domain.user.domain.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleComment extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;
}
