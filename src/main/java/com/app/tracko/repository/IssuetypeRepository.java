package com.app.tracko.repository;

import com.app.tracko.entity.IssuetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuetypeRepository extends JpaRepository<IssuetypeEntity, Long> {
}
