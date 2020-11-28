package com.yogurt;

import com.yogurt.api.auth.controller.AuthController;
import com.yogurt.api.mail.service.MailService;
import com.yogurt.base.security.JwtTokenProvider;
import com.yogurt.api.studio.service.StudioService;
import com.yogurt.api.user.service.UserService;
import groovy.util.logging.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest(
        controllers = {AuthController.class},
        useDefaultFilters = false,
        includeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    value = AuthController.class
            )
        }
)
@Slf4j
public class WebMvcTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
//    @Autowired
            StudioService studioService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    MailService mailService;

    @Autowired
    WebApplicationContext ctx;


    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }


    @Test
    public void testGetUserList() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/auth/studio").contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
    }

}
