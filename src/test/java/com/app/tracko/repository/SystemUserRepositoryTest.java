package com.app.tracko.repository;

import com.app.tracko.entity.SystemUserEntity;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SystemUserRepositoryTest {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup(){
        SystemUserEntity systemUserEntity =
                SystemUserEntity.builder()
                        .systemUserId(1)
                        .password("qweAS123!")
                        .lastName("banu")
                        .firstName("seefa")
                        .emailId("seefa@gmail.com")
                        .build();

        entityManager.persist(systemUserEntity);
    }

    @Test
    @DisplayName("Get data based valid system user id -repo testing")
    public void whenFindById_thenReturnSystemUser(){
        SystemUserEntity systemUserEntity= systemUserRepository.findById(1L).get();
        assertEquals(systemUserEntity.getEmailId(),"seefa@gmail.com ");
    }

}