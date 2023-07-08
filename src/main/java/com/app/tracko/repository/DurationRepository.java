package com.app.tracko.repository;

import com.app.tracko.entity.DurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationRepository extends JpaRepository<DurationEntity, Long> {
}
