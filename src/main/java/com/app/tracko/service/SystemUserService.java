package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.SystemUser;

import java.util.List;


public interface SystemUserService  {


//    List<SystemUserDTO> getAllDetails();
    List<SystemUser> getAllSystemUsers();


    SystemUser createSystemUser(SystemUser systemUser);
    //SystemUserEntity createSystemUser(SystemUserEntity systemUserEntity);

    boolean deleteSystemUsers(Long id);

    SystemUser getSystemUserById(Long id);

    SystemUser updateSystemUser(Long id, SystemUser systemUser);


    SystemUserEntity assignSystemUserToAccessGroup(Long sysid, Long grpid);
}
