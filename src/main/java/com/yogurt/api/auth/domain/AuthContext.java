package com.yogurt.api.auth.domain;

import com.yogurt.api.user.domain.User;
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
