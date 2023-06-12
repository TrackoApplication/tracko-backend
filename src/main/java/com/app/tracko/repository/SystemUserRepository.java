package com.app.tracko.repository;


import com.app.tracko.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {
    Optional<SystemUserEntity> findByResetPasswordToken(String token);
    Optional<SystemUserEntity> findByEmailId(String email);

}
