package com.yogurt;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yogurt.api.auth.controller.AuthController;
import com.yogurt.api.auth.dto.EmailLoginRequest;
import com.yogurt.api.auth.dto.LoginResponse;
import com.yogurt.api.auth.service.AuthService;
import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.user.domain.User;
import com.yogurt.api.user.infra.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
public class AuthControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void loginWithEmail() {
        EmailLoginRequest emailLoginRequest = new EmailLoginRequest();

        LoginResponse loginResponse = LoginResponse.of("qwe", new User(), new Studio());
        when(service.loginWithEmail(emailLoginRequest)).thenReturn(loginResponse);

//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")));
    }
}
