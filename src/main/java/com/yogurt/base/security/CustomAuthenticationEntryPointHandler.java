package com.yogurt.base.security;

import com.google.gson.Gson;
import com.yogurt.base.dto.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ApiResponse apiResponse = ApiResponse.createApiResponse(false, "유효한 인증 토큰을 사용해 주세요.");
        String apiResponseJson = new Gson().toJson(apiResponse);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(401);

        out.print(apiResponseJson);
        out.flush();
    }
}
