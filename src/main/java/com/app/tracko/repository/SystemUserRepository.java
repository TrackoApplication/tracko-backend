package com.app.tracko.repository;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {

//    boolean existsByEmail(String email);
//
//    SystemUser findByEmail(String email);
}
