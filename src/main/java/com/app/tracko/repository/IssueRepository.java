package com.app.tracko.repository;

import com.app.tracko.entity.IssueEntity;
import com.app.tracko.entity.Status;
import jakarta.persistence.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long>{

    @Query("SELECT i FROM IssueEntity i WHERE i.Status = :status")
    IssueEntity findByStatus(@Param("status") String status);

    @Query("SELECT i FROM IssueEntity i WHERE i.Assignee = :assignee")
    IssueEntity findByAssignee(String assignee);


}

