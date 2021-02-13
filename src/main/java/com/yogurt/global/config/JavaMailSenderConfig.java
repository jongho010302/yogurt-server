package com.yogurt.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig extends JavaMailSenderImpl {

    @Value("${yogurt.mail.host}")
    private String mailSenderHost;

    @Value("${yogurt.mail.port}")
    private int mailSenderPort;

    @Value("${yogurt.mail.username}")
    private String mailSenderUsername;

    @Value("${yogurt.mail.password}")
    private String mailSenderPassword;


    @Bean("javaMailSenderImpl")
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(mailSenderHost);
        mailSenderImpl.setPort(mailSenderPort);
        mailSenderImpl.setUsername(mailSenderUsername);
        mailSenderImpl.setPassword(mailSenderPassword);

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.debug", true);
        properties.put("mail.smtp.host", mailSenderHost);
        properties.put("mail.smtp.port", mailSenderPort);
        properties.put("mail.smtp.socketFactory.port", mailSenderPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        mailSenderImpl.setJavaMailProperties(properties);

        return mailSenderImpl;
    }

}
