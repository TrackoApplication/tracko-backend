package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;
import com.app.tracko.service.SystemUserService;
import com.app.tracko.repository.SystemUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SystemUserServiceTest {

    @Autowired
    private  SystemUserService systemUserService;
    @MockBean
    private  SystemUserRepository systemUserRepository;


    @BeforeEach
    void setUp() {
        SystemUserEntity systemUserEntity = SystemUserEntity
                .builder()
                .systemUserId(3)
                .emailId("seefabanu@gmail.com")
                .userName("seefaBanu")
                .firstName("Seefa")
                .lastName("Banu")
                .password("qweAS123!")
                .build();

        Mockito.when(systemUserRepository.findById(3L))
                .thenReturn(Optional.ofNullable(systemUserEntity));
    }

    @Test
    @DisplayName(("Get data based valid System User Id"))
//    to disable
//    @Disabled
    public void whenValidSystemUserID_thenSystemUserShouldFound() throws SystemUserNotFoundException {
        long systemUserId = 3;
        SystemUserEntity found = systemUserService.getSystemUserById(systemUserId);

        assertEquals(systemUserId,found.getSystemUserId());
    }

}