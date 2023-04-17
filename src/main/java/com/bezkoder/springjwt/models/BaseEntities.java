package com.bezkoder.springjwt.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

}
