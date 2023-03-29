package com.app.tracko.controller;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.SystemUser;
//import com.app.tracko.service.EmailService;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.EmailMessage;
import com.app.tracko.service.EmailSenderService;
import com.app.tracko.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SystemUserController {
    @Autowired
    private final SystemUserService systemUserService;

    private final SystemUserRepository systemUserRepository;

//    private final EmailService emailService;
    private final EmailSenderService emailSenderService;


    public SystemUserController(SystemUserService systemUserService, EmailSenderService emailSenderService, SystemUserRepository systemUserRepository, SystemUserRepository systemUserRepository1) {
        this.systemUserService = systemUserService;
        this.emailSenderService = emailSenderService;
        this.systemUserRepository = systemUserRepository1;
    }

    @PostMapping("/sendemail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
        this.emailSenderService.sendEmail(
                emailMessage.getToEmail(),
                emailMessage.getBody(),
                emailMessage.getSubject());
        return ResponseEntity.ok("success");
    }

    @PostMapping("/systemusers")
    public SystemUser createSystemUser(@RequestBody SystemUser systemUser){
        return systemUserService.createSystemUser(systemUser);
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
    public List<SystemUserEntity> getAllSystemUsers(){
      return systemUserRepository.findAll();
    }




    @DeleteMapping("/systemusers/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
            boolean deleted = false;
            deleted = systemUserService.deleteSystemUsers(id);
            Map<String,Boolean> response = new HashMap<>();
            response.put("deleted",deleted);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/systemusers/{id}")
    public ResponseEntity<SystemUser> getSystemUserById(@PathVariable Long id){
        SystemUser systemUser = systemUserService.getSystemUserById(id);
//        .orElseThrow(()-> new ConfigDataResourceNotFoundException("systemusers not exist with id :"+ id));
        return ResponseEntity.ok(systemUser);
    }

    @PutMapping("/systemusers/{id}")
    public ResponseEntity<SystemUser> updateSystemUser(@PathVariable Long id,
                                                       @RequestBody SystemUser systemUser){
        systemUser = systemUserService.updateSystemUser(id, systemUser);
        return ResponseEntity.ok(systemUser);
    }

    @PutMapping("/systemusers/{sysid}/{grpid}")
    public SystemUserEntity assignSystemUserToAccessGroup(
            @PathVariable Long sysid,
            @PathVariable Long grpid
    ){
        return systemUserService.assignSystemUserToAccessGroup(sysid,grpid);
    }





}
