package com.yogurt.inquiry.domain;

import com.yogurt.generic.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Inquiry extends BaseEntity {

    @Column(nullable = false)
    private Long studioId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String anonymous_user_name;

    @Column(nullable = false)
    private String author_ip;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String authorName;

    @Column(nullable = false)
    private int viewCount;

}
