package com.app.tracko.repository;
import com.app.tracko.entity.EpicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicRepository extends JpaRepository<EpicEntity,Long> {

}
