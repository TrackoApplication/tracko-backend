package com.app.tracko.repository;

import com.app.tracko.entity.AccessGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroupEntity,Long> {

    AccessGroupEntity findByAccessGroupName(String i);
}
