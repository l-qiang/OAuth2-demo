package com.sinotrans.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public @Data class BaseEntity {
    
    @CreatedDate
    @Column(name="CREATE_DATE")
    protected Date createDate;
    
    @CreatedBy
    @Column(name="CREATE_BY")
    protected String createBy;
    
    @LastModifiedDate
    @Column(name="LAST_MODIFY_DATE")
    protected Date lastModifyDate;
    
    @LastModifiedBy
    @Column(name="LAST_MODIFIED_BY")
    protected String lastModifiedBy;
    
    @Version
    @Column(name="VERSION")
    protected int version;
}
