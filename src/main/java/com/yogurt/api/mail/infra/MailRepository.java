package com.yogurt.api.mail.infra;

import com.yogurt.api.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
