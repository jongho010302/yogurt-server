package com.yogurt.generic.domain;

import static org.hamcrest.MatcherAssert.assertThat;

import com.yogurt.generic.user.domain.Email;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void valid_email() {
        Email.of("jongho.dev@gmail.com");
    }

    @Test
    public void getEmail() {
        final Email email = Email.of("jongho.dev@gmail.com");
//        assertThat(email, is);
    }
}
