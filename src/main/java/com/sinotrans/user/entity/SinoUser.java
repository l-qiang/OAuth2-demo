package com.sinotrans.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.sinotrans.common.entity.BaseEntity;
import com.sinotrans.role.entity.SinoRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="SINO_USER")
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public @Data class SinoUser extends BaseEntity{
    
    @Id
    @Column(name="USERNAME")
    @Size(max=64)
    private String username;
    
    @Column(name="PASSWORD")
    @Size(max=128)
    private String password;

    @Column(name="EMAIL")
    @Email
    @Size(max=128)
    private String email;
    
    @Column(name="MOBILE")
    @Size(max=11)
    private String mobile;
    
    @ManyToMany
    private Set<SinoRole> roles = new HashSet<>();  
}
