package com.app.tracko.repository;

import com.app.tracko.entity.EpicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpicRepository extends JpaRepository<EpicEntity,Long> {
}
