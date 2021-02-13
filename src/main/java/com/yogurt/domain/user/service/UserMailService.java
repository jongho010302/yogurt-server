package com.yogurt.domain.user.service;

import com.yogurt.domain.mail.service.MailService;
import com.yogurt.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserMailService {

    private final MailService mailService;

    public void changePasswordSendMail(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        mailService.send("/change-password", dataMap, user.getName(), user.getEmail());
    }

    public void changeEmailSendMail(User user, String originalEmail, String changedEmail) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", user.getName());
        dataMap.put("originalEmail", originalEmail);
        dataMap.put("changedEmail", changedEmail);
        mailService.send("/change-email", dataMap, user.getName(), user.getEmail());
    }
}
