package com.app.tracko.service;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.model.AccessGroup;
import com.app.tracko.model.SystemUser;

import com.app.tracko.repository.AccessGroupRepository;
import com.app.tracko.repository.SystemUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceImpl implements SystemUserService{

    private SystemUserRepository systemUserRepository;
    private AccessGroupRepository accessGroupRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

//
//    @Override
//    public List<SystemUserDTO> getAllDetails() {
//        List<SystemUserEntity> systemUserEntities = systemUserRepository.findAll();
//        return systemUserEntities.stream()
//                .map(sysUser -> {
//                    String groupName = sysUser.getAccessGroups().stream()
//                            .map(AccessGroupEntity::getAccessGroupName)
//                            .collect(Collectors.joining(", "));
//                    return new SystemUserDTO(sysUser.getSystemUserId(), sysUser.getFirstName(),groupName);
//                })
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<SystemUserDTO> getAllDetails() {
//      List<SystemUserEntity> systemUserEntities= systemUserRepository.findAll();
//      List<SystemUserDTO> systemUserDTOList = new ArrayList<>();
//      for(SystemUserEntity systemUserEntity : systemUserEntities ){
//          Set<String> accessGroupNames = new HashSet<>();
//          for(AccessGroupEntity accessGroup : systemUserEntity.getAccessGroups()){
//              accessGroupNames.add(accessGroup.getAccessGroupName());
//          }
//          systemUserDTOList.add(new SystemUserDTO(systemUserEntity,accessGroupNames));
//      }
//      return systemUserDTOList;
//    }



//    @Override
//    public List<SystemUser> getAllSystemUsers() {
//        List<SystemUserEntity> systemUserEntities
//                = systemUserRepository.findAll();
//        List<SystemUser> systemUsers= systemUserEntities.stream().
//                map(sysUser-> new SystemUser(
//                        sysUser.getSystemUserId(),
//                        sysUser.getFirstName(),
//                        sysUser.getLastName(),
//                        sysUser.getUserName(),
//                        sysUser.getPassword(),
//                        sysUser.getEmailId(),
//                        sysUser.getAccessGroups() )).collect(Collectors.toList()
//                );
//        return systemUsers;
//    }

//    @Override
//    public List<SystemUser> getAllSystemUsers() {
//       return systemUserRepository.findAll();
//    }


    @Override
    public List<SystemUser> getAllSystemUsers() {
        return null;
    }

    @Override
    public SystemUser createSystemUser(@RequestBody SystemUser systemUser) {
        SystemUserEntity systemUserEntity= new SystemUserEntity();
        BeanUtils.copyProperties(systemUser, systemUserEntity);
        systemUserRepository.save(systemUserEntity);
        return systemUser;
    }

//    @Override
//    public SystemUserEntity createSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
//        systemUserRepository.save(systemUserEntity);
//        return systemUserEntity;
//    }

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
//        systemUserEntity.setPassword(systemUser.getPassword());
        systemUserEntity.setEmailId(systemUser.getEmailId());

        systemUserRepository.save(systemUserEntity);

        return systemUser;
    }

    @Override
    public SystemUserEntity assignSystemUserToAccessGroup(Long sysid, Long grpid) {
        return null;
    }


}
