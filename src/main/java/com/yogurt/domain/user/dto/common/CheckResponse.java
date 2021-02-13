package com.yogurt.domain.user.dto.common;

import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckResponse {

    private User user;
    private Studio studio;

    public static CheckResponse of( User user, Studio studio) {
        return new CheckResponse(user, studio);
    }
}
