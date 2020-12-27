package com.yogurt.generic.base;

import com.yogurt.base.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public void deleted() {
        Date currentDate = DateUtils.getCurrentDate();
        setDeletedAt(currentDate);
        setIsDeleted(true);
    }
}
