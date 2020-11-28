package com.yogurt.api.mail.domain;

import com.yogurt.generic.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Mail extends BaseEntity {

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String toName;

    @Column(nullable = false)
    private String toEmail;

    @Column
    private String fromName;

    @Column
    private String fromEmail;

}
