package com.yogurt.global.security;

import com.yogurt.domain.auth.domain.TokenBlacklist;
import com.yogurt.domain.auth.infra.TokenBlacklistRepository;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenBlacklistRepository tokenBlacklistRepository;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken(request);

        if (token == null || token == "") {
            chain.doFilter(request, response);
            return;
        }

        if (!jwtTokenProvider.validateToken(token)) {
            ApiResponse apiResponse = ApiResponse.createSuccessApiResponse("유효하지 않은 인증 토큰입니다.");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JsonUtils.convertObjectToJson(apiResponse));
            response.flushBuffer();
            return;
        }

        Optional<TokenBlacklist> tokenBlacklist = tokenBlacklistRepository.findByToken(token);

        if (tokenBlacklist.isPresent()) {
            ApiResponse apiResponse = ApiResponse.createSuccessApiResponse("로그아웃된 유저의 인증 토큰입니다.");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JsonUtils.convertObjectToJson(apiResponse));
            response.flushBuffer();
            return;
        }

        // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}