package com.app.tracko.service;

import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.SystemUser;
import com.app.tracko.repository.SystemUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceImpl implements SystemUserService{

    private SystemUserRepository systemUserRepository;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public List<SystemUser> getAllSystemUsers() {
        List<SystemUserEntity> systemUserEntities
                = systemUserRepository.findAll();
        List<SystemUser> systemUsers= systemUserEntities.stream().
                map(sysUser-> new SystemUser(sysUser.getSystemUserId(),
                        sysUser.getFirstName(),
                        sysUser.getLastName(),
                        sysUser.getUserName(),
                        sysUser.getPassword(),
                        sysUser.getEmailId() )).collect(Collectors.toList()
                );
        return systemUsers;
    }

    @Override
    public SystemUser createSystemUser(SystemUser systemUser) {
        SystemUserEntity systemUserEntity= new SystemUserEntity();
        BeanUtils.copyProperties(systemUser, systemUserEntity);
        systemUserRepository.save(systemUserEntity);
        return systemUser;
    }

    @Override
    public boolean deleteSystemUsers(Long id) {
        SystemUserEntity systemUser = systemUserRepository.findById(id).get();
        systemUserRepository.delete(systemUser);
        return true;

    }

    @Override
    public SystemUser getSystemUserById(Long id) {
        SystemUserEntity systemUserEntity
                = systemUserRepository.findById(id).get();

        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserEntity, systemUser);
        return systemUser;
    }

    @Override
    public SystemUser updateSystemUser(Long id, SystemUser systemUser) {
        SystemUserEntity systemUserEntity
                = systemUserRepository.findById(id).get();
        systemUserEntity.setFirstName(systemUser.getFirstName());
        systemUserEntity.setLastName(systemUser.getLastName());
        systemUserEntity.setUserName(systemUser.getUserName());
        systemUserEntity.setPassword(systemUser.getPassword());
        systemUserEntity.setEmailId(systemUser.getEmailId());

        systemUserRepository.save(systemUserEntity);
        return systemUser;
    }
}
