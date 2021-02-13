package com.yogurt.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TemplateService {

    private final TemplateEngine templateEngine;


    public String buildSubject(Map<String, Object> data, String templatePath) {
        return build(data, templatePath, "subject");
    }

    public String buildContent(Map<String, Object> data, String templatePath) {
        return build(data, templatePath, "content");
    }

    private String build(Map<String, Object> data, String templatePath, String fragment) {
        Context context = new Context();
        context.setVariables(data);

        Set<String> param = new HashSet<>();
        param.add(fragment);

        return templateEngine.process(templatePath, param, context);
    }
}
