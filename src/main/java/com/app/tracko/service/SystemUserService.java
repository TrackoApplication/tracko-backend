package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;
import com.app.tracko.model.SystemUser;
import com.app.tracko.model.SystemUserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SystemUserService {

    List<SystemUserEntity> getAllSystemUsers();

    SystemUserEntity createSystemUser(SystemUserEntity systemUserEntity);
    //SystemUserEntity createSystemUser(SystemUserEntity systemUserEntity);

    boolean deleteSystemUsers(Long id);

    SystemUserEntity getSystemUserById(Long id) throws SystemUserNotFoundException;

    SystemUser updateSystemUser(Long id, SystemUser systemUser);

     String updateResetPasswordToken(String token,String email) throws SystemUserNotFoundException;
    public void updatePassword(String newPassword, SystemUserEntity systemUserEntity);
    public SystemUserEntity getByResetPasswordToken(String resetPasswordToken);

    ResponseEntity<List<SystemUserDto>> getSystemUsersDetails();
}
