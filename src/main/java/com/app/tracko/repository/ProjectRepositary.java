package com.app.tracko.repository;


import com.app.tracko.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositary extends JpaRepository <ProjectEntity,Long> {

}
