package com.app.tracko.repository;

import com.app.tracko.entity.UserAccessGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessGroupRepository extends JpaRepository<UserAccessGroupEntity, Long> {
}
