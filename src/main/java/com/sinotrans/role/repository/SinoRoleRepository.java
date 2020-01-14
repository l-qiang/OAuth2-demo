package com.sinotrans.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinotrans.role.entity.SinoRole;

@Repository
public interface SinoRoleRepository extends JpaRepository<SinoRole, String>{

}
