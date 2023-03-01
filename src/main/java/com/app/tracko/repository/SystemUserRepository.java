package com.app.tracko.repository;

import com.app.tracko.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {
}
