package com.app.tracko.repository;

import com.app.tracko.entity.PriorityEntity;
import com.app.tracko.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
