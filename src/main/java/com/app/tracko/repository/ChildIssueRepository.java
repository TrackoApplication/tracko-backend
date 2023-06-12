package com.app.tracko.repository;

import com.app.tracko.entity.ChildIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildIssueRepository extends JpaRepository<ChildIssueEntity , Long> {

}
