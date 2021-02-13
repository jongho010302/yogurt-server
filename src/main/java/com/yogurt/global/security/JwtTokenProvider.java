package com.yogurt.global.security;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.user.domain.User;
import com.yogurt.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${yogurt.jwt.secretKey}")
    private String secretKey;

    // 토큰 유효 시간: 일주일
    private long tokenValidTime = 60 * 60 * 24 * 7 * 1000L;

    private final UserDetailService userDetailService;


    public String createToken(String userPk, String role, long studioId) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("role", role);
        claims.put("studioId", studioId);
        Date now = DateUtils.getCurrentDate();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Transactional
    public Authentication getAuthentication(String token) {
        User user = userDetailService.getByEmail(getUserEmailFromToken(token));
        long studioId = getStudioIdFromToken(token);
        AuthContext authContext = AuthContext.of(user, studioId);
        Collection<? extends GrantedAuthority> collection = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UsernamePasswordAuthenticationToken(authContext, "", collection);
    }

    private String getUserEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    private long getStudioIdFromToken(String token) {
        Integer studioId = (Integer) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("studioId");
        return studioId.longValue();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(DateUtils.getCurrentDate());
        } catch (Exception e) {
            return false;
        }
    }
}