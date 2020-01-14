package com.sinotrans.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinotrans.user.entity.SinoUser;

@Repository
public interface SinoUserRepository extends JpaRepository<SinoUser, String>{

}
