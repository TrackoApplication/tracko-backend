package com.app.tracko.controller;

import com.app.tracko.entity.AccessEntity;
import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;


import com.app.tracko.model.AccessDto;
import com.app.tracko.model.SystemUserDto;
import com.app.tracko.model.SystemUserUpdateRequest;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.EmailSender;
import com.app.tracko.service.SystemUserService;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001","http://localhost:3002","http://localhost:3002"})
@RestController
@RequestMapping("/api/v1")
public class SystemUserController {

    private final SystemUserService systemUserService;
    private final SystemUserRepository systemUserRepository;
    private final AccessGroupRepository accessGroupRepository;

    private final JavaMailSender mailSender;

    public SystemUserController(SystemUserService systemUserService, SystemUserRepository systemUserRepository, AccessGroupRepository accessGroupRepository, JavaMailSender mailSender) {
        this.systemUserService = systemUserService;
        this.systemUserRepository = systemUserRepository;
        this.accessGroupRepository = accessGroupRepository;
        this.mailSender = mailSender;
    }



    @GetMapping("/systemusersDto")
    public ResponseEntity<List<SystemUserDto>> getSystemUsersDetails() {
        return systemUserService.getSystemUsersDetails();
    }


    @DeleteMapping("/systemusers/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            SystemUserEntity systemUserEntity = systemUserRepository.findById(id).orElseThrow(() -> new SystemUserNotFoundException( "User not found"));
            systemUserEntity.setIsDeleted(true);
            systemUserRepository.save(systemUserEntity);
            return ResponseEntity.ok("User deleted successfully");
        } catch (SystemUserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/systemusers/{id}")
    public ResponseEntity<SystemUserDto> getSystemUserDetailsById(@PathVariable Long id) throws SystemUserNotFoundException {
        return systemUserService.getSystemUsersDetailsById(id);
    }

    @PutMapping("/systemusers/{id}")
    public ResponseEntity<SystemUserDto> updateSystemUser(@PathVariable Long id, @RequestBody SystemUserUpdateRequest request) throws MessagingException, UnsupportedEncodingException {
        SystemUserDto systemUserDto = systemUserService.updateSystemUser(id, request.getSystemUserDto(), request.getAccessGroup());
        return ResponseEntity.ok(systemUserDto);
    }



    @PostMapping("/auth/forgotpassword")
    public ResponseEntity<String> processForgotPassword(@RequestBody Map<String, String> request) throws SystemUserNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.get("email");
        UUID randomUUID = UUID.randomUUID();
        String token = randomUUID.toString().replaceAll("_", "");

        systemUserService.updateResetPasswordToken(token, email);
        Optional<SystemUserEntity> userR = systemUserRepository.findByEmailId(email);

        if (userR.isPresent()) {
            String name = userR.get().getFirstName();

            EmailSender emailSender = new EmailSender(mailSender);
            String resetPasswordLink = "http://localhost:3000/reset_password?token=" + token;
            String subject = "Here is your link to reset your password";
            String content = "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Hello "+name+" ,</p>"
                    + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">You have requested to reset your password</p>"
                    + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Click the button below to reset your password:</p>"
                    + "<p><a href=\"" + resetPasswordLink + "\" style=\"display: inline-block; font-family: Arial, sans-serif; font-size: 14px; color: #ffffff; background-color: #007bff; text-decoration: none; padding: 10px 20px; border-radius: 5px;\">Change my Password</a></p>"
                    + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">If you didn't request this, please ignore this email.</p>";

            emailSender.sendEmail(email, subject, content);
            return ResponseEntity.ok("Email sent successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }


    @PostMapping("/auth/resetpassword")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String password = request.get("password");
        SystemUserEntity systemUserEntity = systemUserService.getByResetPasswordToken(token);

        systemUserService.updatePassword(password, systemUserEntity);

        return "you have changed the password";

    }

    @GetMapping("/getAccessGroupOfAUser/{id}")
    public ResponseEntity<List<String>> getAccessGroupOfAUser(@PathVariable Long id) {
        try {
            SystemUserEntity systemUserEntity = systemUserRepository.findById(id).orElseThrow(NoSuchElementException::new);
            List<AccessGroupEntity> accessGroupEntities = systemUserEntity.getAccessGroupEntities();
            List<String> accessGroupNames = new ArrayList<>();
            for (AccessGroupEntity a : accessGroupEntities) {
                String b = a.getAccessGroupName();
                accessGroupNames.add(b);
            }
            return ResponseEntity.ok(accessGroupNames);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
