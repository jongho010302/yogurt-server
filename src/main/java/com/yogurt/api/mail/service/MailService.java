package com.yogurt.api.mail.service;

import com.yogurt.file.FileService;
import com.yogurt.api.mail.domain.Mail;
import com.yogurt.api.mail.infra.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSenderImpl javaMailSenderImpl;

    private final TemplateService templateService;

    private final MailRepository mailRepository;

    private final FileService fileService;

    private final String defaultFromName = "Yogurt 고객센터";

    private final String defaultFromEmail = "jongjjang03@naver.com";


    public String send(String templatePath, Map<String, Object> data, String toName, String toEmail) {

        Mail mail = new Mail();
        mail.setSubject(templateService.buildSubject(data, templatePath));
        mail.setContent(templateService.buildContent(data, templatePath));
        mail.setToName(toName);
        mail.setToEmail(toEmail);
        mail.setFromName(defaultFromName);
        mail.setFromEmail(defaultFromEmail);

        // Insert Mail
        mailRepository.save(mail);

        // Send Mail
        sendMail(mail);

        return "Success";
    }

    public void sendMail(Mail mail) {
        try {
            new Thread(() -> {
                try {
                    String subject = mail.getSubject();
                    String content = mail.getContent();

                    String toName = mail.getToName();
                    String toEmail = mail.getToEmail();

                    String fromName = mail.getFromName();
                    String fromEmail = mail.getFromEmail();

                    InternetAddress fromAddress = new InternetAddress(fromEmail, fromName);
                    InternetAddress toAddress = new InternetAddress(toEmail, toName);

                    MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
                    mimeMessage.setFrom(fromAddress);
                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(content, "UTF-8", "html");
                    mimeMessage.addRecipient(RecipientType.TO, toAddress);

                    javaMailSenderImpl.send(mimeMessage);

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }).start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
