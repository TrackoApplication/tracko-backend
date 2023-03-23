package com.app.tracko.service;

import com.app.tracko.model.SystemUser;

import java.util.List;


public interface SystemUserService  {
    List<SystemUser> getAllSystemUsers();

    SystemUser createSystemUser(SystemUser systemUser);

    boolean deleteSystemUsers(Long id);

    SystemUser getSystemUserById(Long id);

    SystemUser updateSystemUser(Long id, SystemUser systemUser);


}
