package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;

import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.SystemUserRepository;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;
    private final AccessGroupRepository accessGroupRepository;
    private final JavaMailSender mailSender;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository, AccessGroupRepository accessGroupRepository, JavaMailSender mailSender) {
        this.systemUserRepository = systemUserRepository;
        this.accessGroupRepository = accessGroupRepository;
        this.mailSender = mailSender;
    }


//    @Override
//    public List<SystemUserEntity> getAllSystemUsers() {
//        return systemUserRepository.findAll();
//    }
//

//    @Override
//    public boolean deleteSystemUsers(Long id) {
//        SystemUserEntity systemUser = systemUserRepository.findById(id).get();
//        systemUserRepository.delete(systemUser);
//        return true;
//    }


    @Override
    public SystemUserDto updateSystemUser(Long id, SystemUserDto systemUserDto,String accessGroup) throws MessagingException, UnsupportedEncodingException {
        SystemUserEntity systemUserEntity = systemUserRepository.findById(id).get();

        systemUserEntity.setFirstName(systemUserDto.getFirstName());
        systemUserEntity.setLastName(systemUserDto.getLastName());


        String email = systemUserDto.getEmailId();
        String name = systemUserDto.getFirstName();
        String login = "http://localhost:8080/api/v1/auth/authenticate";
        String subject = "Access Group Assignment";
        String content = "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Hello " + name + ",</p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">You have been assigned to the following Access Group:</p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\"><strong>" + accessGroup + "</strong></p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">Please click the button below to proceed:</p>"
                + "<p><a href=\"" + login + "\" style=\"display: inline-block; font-family: Arial, sans-serif; font-size: 14px; color: #ffffff; background-color: #007bff; text-decoration: none; padding: 10px 20px; border-radius: 5px;\">Go to Access Group</a></p>"
                + "<p style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">If you have any questions, please contact the administrator.</p>";


        EmailSender emailSender = new EmailSender(mailSender);
        systemUserEntity.setEmailId(systemUserDto.getEmailId());
        systemUserRepository.save(systemUserEntity);
        if (accessGroup.equals("Product Owner")) {
            AccessGroupEntity accessGroupEntity = accessGroupRepository.findByAccessGroupName(accessGroup);
            accessGroupEntity.addUserToAccessGroup(systemUserEntity);
            accessGroupRepository.save(accessGroupEntity);
            emailSender.sendEmail(email, subject, content);
        }

        return systemUserDto;
    }

    @Override
    public String updateResetPasswordToken(String token, String email) throws SystemUserNotFoundException {

        Optional<SystemUserEntity> optionalSystemUserEntity = systemUserRepository.findByEmailId(email);
        if (optionalSystemUserEntity.isPresent()) {
            SystemUserEntity systemUserEntity = optionalSystemUserEntity.get();
            systemUserEntity.setResetPasswordToken(token);
            systemUserRepository.save(systemUserEntity);
            return "hi";
        } else {
            throw new SystemUserNotFoundException("Could not find user with email");
        }
    }


    public SystemUserEntity getByResetPasswordToken(String resetPasswordToken) {
        return systemUserRepository.findByResetPasswordToken(resetPasswordToken).get();
    }

    public void updatePassword(String newPassword, SystemUserEntity systemUserEntity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPassword);
        systemUserEntity.setPassword(encodePassword);
        systemUserRepository.save(systemUserEntity);
    }
    @Override
    public ResponseEntity<SystemUserDto> getSystemUsersDetailsById(Long id) {
        SystemUserEntity user = systemUserRepository.findById(id).get();
        SystemUserDto systemUserDto = new SystemUserDto();
        systemUserDto.setSystemUserId(user.getSystemUserId());
        systemUserDto.setFirstName(user.getFirstName());
        List<AccessGroupEntity> accessGroupEntities = user.getAccessGroupEntities();
        List<String> accesGroups = new ArrayList<>();
        for (AccessGroupEntity a : accessGroupEntities){
            String x = a.getAccessGroupName();
            accesGroups.add(x);
        }
        systemUserDto.setAccessGroups(accesGroups);
        systemUserDto.setLastName(user.getLastName()); // Corrected line
        systemUserDto.setEmailId(user.getEmailId());
        systemUserDto.setRole(user.getRole());

        return ResponseEntity.ok(systemUserDto);
}


    @Override
    public ResponseEntity<List<SystemUserDto>> getSystemUsersDetails() {
        List<SystemUserEntity> systemUserEntity = systemUserRepository.findAll();
        List<SystemUserDto> systemUserDtos = new ArrayList<>();

        for (SystemUserEntity user : systemUserEntity) {
            if (user.getIsDeleted() == null) {
                SystemUserDto systemUserDto = new SystemUserDto();
                systemUserDto.setSystemUserId(user.getSystemUserId());
                systemUserDto.setFirstName(user.getFirstName());
                List<AccessGroupEntity> accessGroupEntities = user.getAccessGroupEntities();
                List<String> accesGroups = new ArrayList<>();
                for (AccessGroupEntity a : accessGroupEntities){
                    String x = a.getAccessGroupName();
                    accesGroups.add(x);
                }
                systemUserDto.setAccessGroups(accesGroups);
                systemUserDto.setLastName(user.getLastName()); // Corrected line
                systemUserDto.setEmailId(user.getEmailId());
                systemUserDto.setRole(user.getRole());


                systemUserDtos.add(systemUserDto);
            }
        }

        return ResponseEntity.ok(systemUserDtos);
    }



    }
