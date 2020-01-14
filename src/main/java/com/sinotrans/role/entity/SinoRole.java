package com.sinotrans.role.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.sinotrans.common.entity.BaseEntity;
import com.sinotrans.user.entity.SinoUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="SINO_ROLE")
@EqualsAndHashCode(callSuper=true)
public @Data class SinoRole extends BaseEntity{
    
    @Id
    @Column(name="CODE")
    @Size(max=64)
    private String code;
    
    @Column(name="NAME")
    @Size(max=256)
    private String name;
    
    @ManyToMany(mappedBy="roles")
    private Set<SinoUser> users = new HashSet<>();
}
