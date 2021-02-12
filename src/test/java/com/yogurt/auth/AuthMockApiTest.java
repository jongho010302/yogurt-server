package com.yogurt.auth;

import com.yogurt.api.auth.controller.AuthController;
import com.yogurt.generic.user.domain.Email;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(AuthController.class)
public class AuthMockApiTest {

//    private signup_invalid_email() throws Exception {
//        //given
//        final Email email = Email.of("jongho.dev@gmail.com");
//    }
}
