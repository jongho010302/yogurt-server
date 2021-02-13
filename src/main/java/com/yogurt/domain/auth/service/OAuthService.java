package com.yogurt.domain.auth.service;

import com.yogurt.domain.auth.dto.oauth.FacebookOAuthResponse;
import com.yogurt.domain.auth.dto.oauth.GoogleOAuthResponse;
import com.yogurt.domain.auth.exception.OAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthService {

    private final RestTemplate restTemplate;

    public GoogleOAuthResponse requestGoogleOAuth(String accessToken) {
        try {
            return restTemplate.getForObject("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken, GoogleOAuthResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OAuthException();
        }
    }

    public FacebookOAuthResponse requestFacebookOAuth(String accessToken) {
        try {
            return restTemplate.getForObject("https://graph.facebook.com/me?fields=name,email&access_token=" + accessToken, FacebookOAuthResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OAuthException();
        }
    }
}
