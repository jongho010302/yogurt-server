package com.yogurt.member.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.user.domain.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;
}
