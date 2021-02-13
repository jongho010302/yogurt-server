package com.yogurt;

import com.yogurt.domain.auth.dto.request.EmailLoginRequest;
import com.yogurt.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithEmail() throws Exception {
        EmailLoginRequest emailLoginRequest = new EmailLoginRequest();
        emailLoginRequest.setEmail("jongho.dev@gmail.com");
        emailLoginRequest.setPassword("Wldms0302!");
        emailLoginRequest.setStudioId(1L);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/tokens/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(emailLoginRequest)))
                .andExpect(status().isOk());
    }
}
