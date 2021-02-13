package com.yogurt.domain.auth.domain;

import com.yogurt.domain.base.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenBlacklist extends BaseEntity {

    @Column(nullable = false)
    private String token;

    public static TokenBlacklist of(String token) {
        return new TokenBlacklist(token);
    }
}
