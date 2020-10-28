package com.yogurt;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Transactional
@AutoConfigureMockMvc
public class AuthControllerTest2 {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    @Autowired
    TestRestTemplate restTemplate;

//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .alwaysDo(print())
//                .build();
//    }
//
//    @Test
//    public void getStudios() {
//        ResponseEntity<ApiResponse> response = restTemplate.getForEntity("/auth/studio", ApiResponse.class);
//        assertTrue(response.getStatusCode().is2xxSuccessful());
//    }
//
//    @Test
//    public void verifyUsername() {
//        VerifyUsernameRequest verifyUsernameRequest = new VerifyUsernameRequest();
//        verifyUsernameRequest.setUsername("jongjjang03");
//
//        ResponseEntity<ExceptionResponse> response = restTemplate.postForEntity("/auth/verify-username", verifyUsernameRequest, ExceptionResponse.class);
//        assertTrue(response.getBody().getErrorCode().equals("ALREADY_DATA_EXISTS"));
//    }
//
//    @Test
//    public void signUpStudioError() {
//        SignupRequest signupRequest = new SignupRequest();
//        signupRequest.setStudioId(0);
//
//        ResponseEntity<ExceptionResponse> response = restTemplate.postForEntity("/auth/sign-up", signupRequest, ExceptionResponse.class);
//        assertTrue(response.getBody().getErrorCode().equals("DATA_NOT_EXISTS"));
//    }
//
//    @Test
//    public void signUpUsernameError() {
//        SignupRequest signupRequest = new SignupRequest();
//        signupRequest.setStudioId(1);
//        signupRequest.setUsername("jongjjang03");
//
//        ResponseEntity<ExceptionResponse> response = restTemplate.postForEntity("/auth/sign-up", signupRequest, ExceptionResponse.class);
//        assertTrue(response.getBody().getErrorCode().equals("ALREADY_DATA_USE"));
//    }
//
//    @Test
//    public void signUpEmailrror() {
//        SignupRequest signupRequest = new SignupRequest();
//        signupRequest.setStudioId(1);
//        signupRequest.setUsername("anythingCanPass");
//        signupRequest.setUsername("jongjjang03@naver.com");
//
//        ResponseEntity<ExceptionResponse> response = restTemplate.postForEntity("/auth/sign-up", signupRequest, ExceptionResponse.class);
//        assertTrue(response.getBody().getErrorCode().equals("ALREADY_DATA_USE"));
//    }
}
