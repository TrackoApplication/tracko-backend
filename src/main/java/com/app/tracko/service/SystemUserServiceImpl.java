package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;
import com.app.tracko.model.SystemUser;

import com.app.tracko.model.SystemUserDto;
import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.SystemUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;
    private AccessGroupRepository accessGroupRepository;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }


    @Override
    public List<SystemUserEntity> getAllSystemUsers() {
        return systemUserRepository.findAll();
    }

    @Override
    public SystemUserEntity createSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
        systemUserRepository.save(systemUserEntity);
        return systemUserEntity;
    }


    @Override
    public boolean deleteSystemUsers(Long id) {
        SystemUserEntity systemUser = systemUserRepository.findById(id).get();
        systemUserRepository.delete(systemUser);
        return true;
    }

    @Override
    public SystemUserEntity getSystemUserById(Long id) throws SystemUserNotFoundException {
        Optional<SystemUserEntity> systemUserEntity = systemUserRepository.findById(id);
        if (!systemUserEntity.isPresent()) {
            throw new SystemUserNotFoundException("Systemuser Not Available");
        }

        return systemUserEntity.get();
    }

    @Override
    public SystemUser updateSystemUser(Long id, SystemUser systemUser) {
        SystemUserEntity systemUserEntity = systemUserRepository.findById(id).get();
        systemUserEntity.setFirstName(systemUser.getFirstName());
        systemUserEntity.setLastName(systemUser.getLastName());
        systemUserEntity.setAccessGroup(systemUser.getAccessGroup());
        systemUserEntity.setResetPasswordToken(systemUser.getResetPasswordToken());
//        systemUserEntity.setPassword(systemUser.getPassword());
        systemUserEntity.setEmailId(systemUser.getEmailId());

        systemUserRepository.save(systemUserEntity);

        return systemUser;
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


    public SystemUserEntity getByResetPasswordToken(String resetPasswordToken){
        return systemUserRepository.findByResetPasswordToken(resetPasswordToken).get();
    }

    public void updatePassword(String newPassword, SystemUserEntity systemUserEntity){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPassword);
        systemUserEntity.setPassword(encodePassword);
        systemUserRepository.save(systemUserEntity);
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
                systemUserDto.setLastName(user.getLastName()); // Corrected line
                systemUserDto.setEmailId(user.getEmailId());
                systemUserDto.setRole(user.getRole());
                systemUserDto.setAccessGroup(user.getAccessGroup());

                systemUserDtos.add(systemUserDto);
            }
        }

        return ResponseEntity.ok(systemUserDtos);
    }



}
