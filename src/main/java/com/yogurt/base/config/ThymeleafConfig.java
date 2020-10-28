package com.yogurt.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.PostConstruct;

@Configuration
public class ThymeleafConfig {

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public ThymeleafConfig(SpringTemplateEngine springTemplateEngine) {
        this.templateEngine = springTemplateEngine;
    }

    @PostConstruct
    private ITemplateResolver templateResolver() {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCheckExistence(true);
        templateResolver.setCacheable(false); // 개발 모드에서는 false 운영 모드에서는 true로 할 것.

        templateEngine.addTemplateResolver(templateResolver);
        return templateResolver;
    }

}
