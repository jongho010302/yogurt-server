package com.yogurt.domain.mail.domain;

import com.yogurt.generic.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class MailAttach extends BaseEntity {

    @ManyToOne
    private Mail mail;

    // 권한
    @Column(nullable = false)
    private String path;

    // 권한
    @Column(nullable = false)
    private String name;

}
