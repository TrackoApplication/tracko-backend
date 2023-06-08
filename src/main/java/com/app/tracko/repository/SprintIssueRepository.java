package com.app.tracko.repository;

import com.app.tracko.entity.SprintIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintIssueRepository extends JpaRepository<SprintIssueEntity, Long> {
}
