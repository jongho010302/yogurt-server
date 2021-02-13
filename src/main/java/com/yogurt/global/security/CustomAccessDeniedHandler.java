package com.yogurt.global.security;

import com.google.gson.Gson;
import com.yogurt.global.common.response.ApiResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ApiResponse apiResponse = ApiResponse.createApiResponse(false, "해당 API 에 접근 권한이 없습니다.");
        String apiResponseJson = new Gson().toJson(apiResponse);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(403);

        out.print(apiResponseJson);
        out.flush();
    }
}
