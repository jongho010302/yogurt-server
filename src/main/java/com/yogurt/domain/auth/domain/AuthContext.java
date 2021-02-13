package com.yogurt.domain.auth.domain;

import com.yogurt.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthContext {

    private User user;

    private long studioId;

    public static AuthContext of(User user, long studioId) {
        return new AuthContext(user, studioId);
    }
}
