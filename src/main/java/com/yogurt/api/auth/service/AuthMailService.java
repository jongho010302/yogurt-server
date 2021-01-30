package com.yogurt.api.auth.service;

import com.yogurt.api.mail.service.MailService;
import com.yogurt.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthMailService {

    private final MailService mailService;

    public void sendMailForSignup(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        mailService.send("/signup-user", dataMap, user.getName(), user.getEmail());
    }
}
