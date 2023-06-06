package com.app.tracko.controller;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;

import com.app.tracko.model.SystemUser;

//import com.app.tracko.service.EmailService;

import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.SystemUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SystemUserController {

    private final SystemUserService systemUserService;
    private final SystemUserRepository systemUserRepository;


    public SystemUserController(SystemUserService systemUserService, SystemUserRepository systemUserRepository) {
        this.systemUserService = systemUserService;
        this.systemUserRepository = systemUserRepository;
    }

//    private final EmailService emailService;
//    private final EmailSenderService emailSenderService;


//    @PostMapping("/sendemail")
//    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
//        this.emailSenderService.sendEmail(
//                emailMessage.getToEmail(),
//                emailMessage.getBody(),
//                emailMessage.getSubject());
//        return ResponseEntity.ok("success");
//    }

    @PostMapping("/systemusers")
    public SystemUserEntity createSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
        return systemUserService.createSystemUser(systemUserEntity);
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody SystemUser systemUser) {
//        SystemUserEntity systemUserEntity = SystemUserService.register(systemUser);
//        if (systemUserEntity == null) {
//            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
//        }
//        emailService.sendVerificationEmail(systemUserEntity.getName(), systemUserEntity.getEmail(), systemUserEntity.getVerificationToken());
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//    }
//
//    @GetMapping("/systemusers")
//    public List<SystemUser> getAllSystemUsers(){
//      return systemUserService.getAllSystemUsers();
//    }

    @GetMapping("/systemusers")
    public List<SystemUserEntity> getAllSystemUsers() {
        return systemUserService.getAllSystemUsers();
    }

    @GetMapping("/systemusersDto")
    public ResponseEntity<List<SystemUserDto>> getSystemUsersDetails() {
        return systemUserService.getSystemUsersDetails();
    }




    @DeleteMapping("/systemusers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean deleted = false;
        deleted = systemUserService.deleteSystemUsers(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("sysytem Deleted Successfully", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/systemusers/{id}")
    public SystemUserEntity getSystemUserById(@PathVariable Long id) throws SystemUserNotFoundException {
        return systemUserService.getSystemUserById(id);
    }

    @PutMapping("/systemusers/{id}")
    public ResponseEntity<SystemUser> updateSystemUser(@PathVariable Long id,
                                                       @RequestBody SystemUser systemUser) {
        systemUser = systemUserService.updateSystemUser(id, systemUser);
        return ResponseEntity.ok(systemUser);
    }


}
