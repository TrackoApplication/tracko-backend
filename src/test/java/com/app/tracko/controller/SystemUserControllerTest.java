//package com.app.tracko.controller;
//
//import com.app.tracko.common.ApiEndpoints;
//import com.app.tracko.entity.SystemUserEntity;
//import com.app.tracko.service.SystemUserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(SystemUserController.class)
//class SystemUserControllerTest{
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SystemUserService systemUserService;
//
//    private SystemUserEntity systemUserEntity;
//    @BeforeEach
//    void setUp() {
//        systemUserEntity= SystemUserEntity.builder()
//                .systemUserId(1)
//                .password("qweAS123!")
//                .lastName("banu")
//                .firstName("seefa")
//                .emailId("seefa@gmail.com")
//                .userName("seefa361")
//                .build();
//
//    }
//
//    @Test
//    void createSystemUser() throws Exception {
//        SystemUserEntity inputSystemUser = SystemUserEntity.builder()
//                .systemUserId(1)
//                .password("qweAS123!")
//                .lastName("Banu")
//                .firstName("Seefa")
//                .emailId("seefa@gmail.com")
//                .userName("Seefa361")
//                .build();
//
//        Mockito.when(systemUserService.createSystemUser(inputSystemUser))
//                .thenReturn(systemUserEntity);
//
//        mockMvc.perform(post(ApiEndpoints.SYSTEM_USERS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "        \"firstName\": \"Seefa\",\n" +
//                        "        \"lastName\": \"Banu\",\n" +
//                        "        \"userName\": \"Seefa361\",\n" +
//                        "        \"password\": \"qweAS123!\",\n" +
//                        "        \"emailId\": \"seefa@gmail.com\"\n" +
//                        "}"))
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    void getAllSystemUsers() {
//    }
//}