package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;
import com.app.tracko.model.SystemUserDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface SystemUserService {


//    SystemUserEntity createSystemUser(SystemUserEntity systemUserEntity);
    //SystemUserEntity createSystemUser(SystemUserEntity systemUserEntity);

//    boolean deleteSystemUsers(Long id);

//    SystemUserEntity getSystemUserById(Long id) throws SystemUserNotFoundException;

    SystemUserDto updateSystemUser(Long id, SystemUserDto systemUserDto,String accessGroup) throws MessagingException, UnsupportedEncodingException;

     String updateResetPasswordToken(String token,String email) throws SystemUserNotFoundException;
    public void updatePassword(String newPassword, SystemUserEntity systemUserEntity);
    public SystemUserEntity getByResetPasswordToken(String resetPasswordToken);

    ResponseEntity<SystemUserDto> getSystemUsersDetailsById(Long id);

    ResponseEntity<List<SystemUserDto>> getSystemUsersDetails();
}
