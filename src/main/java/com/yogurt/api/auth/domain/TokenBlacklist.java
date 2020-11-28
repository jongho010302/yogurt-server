package com.yogurt.api.auth.domain;

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
public class TokenBlacklist extends BaseEntity {

    @Column(nullable = false)
    private String token;

    public static TokenBlacklist of(String token) {
        return new TokenBlacklist(token);
    }
}
