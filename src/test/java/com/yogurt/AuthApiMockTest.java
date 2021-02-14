package com.yogurt;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.yogurt.domain.auth.api.AuthApi;
import com.yogurt.domain.auth.dto.request.EmailLoginRequest;
import com.yogurt.domain.auth.dto.request.LoginResponse;
import com.yogurt.domain.auth.service.AuthService;
import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.user.domain.User;
import com.yogurt.domain.user.infra.admin.AUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthApi.class)
public class AuthApiMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService service;

    @MockBean
    private AUserRepo repository;

    @Test
    public void loginWithEmail() {
        EmailLoginRequest emailLoginRequest = new EmailLoginRequest();

        LoginResponse loginResponse = LoginResponse.of("qwe", new User(), new Studio());
        when(service.loginWithEmail(emailLoginRequest)).thenReturn(loginResponse);

//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")));
    }
}
