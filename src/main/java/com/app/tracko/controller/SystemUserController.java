package com.app.tracko.controller;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;

import com.app.tracko.model.SystemUser;


import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.EmailSender;
import com.app.tracko.service.SystemUserService;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization"})
@RestController
@RequestMapping("/api/v1")
public class SystemUserController {

    private final SystemUserService systemUserService;
    private final SystemUserRepository systemUserRepository;

    private final JavaMailSender mailSender;

    public SystemUserController(SystemUserService systemUserService, SystemUserRepository systemUserRepository, JavaMailSender mailSender) {
        this.systemUserService = systemUserService;
        this.systemUserRepository = systemUserRepository;
        this.mailSender = mailSender;
    }


    @PostMapping("/systemusers")
    public SystemUserEntity createSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
        return systemUserService.createSystemUser(systemUserEntity);
    }


    @GetMapping("/systemusers")
    public List<SystemUserEntity> getAllSystemUsers() {
        return systemUserService.getAllSystemUsers();
    }

    @GetMapping("/systemusersDto")
    public ResponseEntity<List<SystemUserDto>> getSystemUsersDetails() {
        return systemUserService.getSystemUsersDetails();
    }


    @DeleteMapping("/systemusers/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        SystemUserEntity systemUserEntity =systemUserRepository.findById(id).get();
        systemUserEntity.setIsDeleted(true);
        systemUserRepository.save(systemUserEntity);
        return ResponseEntity.ok("user deleted succesfully");
    }

    @GetMapping("/systemusers/{id}")
    public SystemUserEntity getSystemUserById(@PathVariable Long id) throws SystemUserNotFoundException {
        return systemUserService.getSystemUserById(id);
    }

    @PutMapping("/systemusers/{id}")
    public ResponseEntity<SystemUser> updateSystemUser(@PathVariable Long id, @RequestBody SystemUser systemUser) {
        systemUser = systemUserService.updateSystemUser(id, systemUser);
        return ResponseEntity.ok(systemUser);
    }

    //get email id from the forgot password link
    @PostMapping("/auth/forgotpassword")
    public String processForgotPassword(@RequestBody Map<String, String> request) throws SystemUserNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.get("email");
        UUID randomUUID = UUID.randomUUID();
        String token = randomUUID.toString().replaceAll("_", "");

        systemUserService.updateResetPasswordToken(token, email);
        Optional<SystemUserEntity> userR = systemUserRepository.findByEmailId(email);
        Optional<String> userName = userR.map(SystemUserEntity::getFirstName);

            String name = userName.get();

        EmailSender emailSender = new EmailSender(mailSender);
        String resetPasswordLink = "http://localhost:3000/reset_password?token=" + token;
        String subject = "Here is your link to reset your password";
        String content = "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Hello "+name+" ,</p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">You have requested to reset your password</p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Click the button below to reset your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\" style=\"display: inline-block; font-family: Arial, sans-serif; font-size: 14px; color: #ffffff; background-color: #007bff; text-decoration: none; padding: 10px 20px; border-radius: 5px;\">Change my Password</a></p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">If you didn't request this, please ignore this email.</p>";

        emailSender.sendEmail(email, subject, content);
        return "Email sent successfully.";
    }

    @PostMapping("/auth/resetpassword")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String password = request.get("password");
        SystemUserEntity systemUserEntity = systemUserService.getByResetPasswordToken(token);

        systemUserService.updatePassword(password, systemUserEntity);

        return "you have changed the password";

    }

}
