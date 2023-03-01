package com.app.tracko.controller;

import com.app.tracko.model.SystemUser;
import com.app.tracko.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SystemUserController {

    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }
    @PostMapping("/systemusers")
    public SystemUser createSystemUser(@RequestBody SystemUser systemUser){
        return systemUserService.createSystemUser(systemUser);
    }
    @GetMapping("/systemusers")
    public List<SystemUser> getAllSystemUsers(){
        return systemUserService.getAllSystemUsers();
    }
    @DeleteMapping("/systemusers/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
            boolean deleted = false;
            deleted = systemUserService.deleteSystemUsers(id);
            Map<String,Boolean> response = new HashMap<>();
            response.put("deleted",deleted);
            return ResponseEntity.ok(response);
    }
    @GetMapping("/systemusers/{id}")
    public ResponseEntity<SystemUser> getSystemUserById(@PathVariable Long id){
        SystemUser systemUser = null;
        systemUser= systemUserService.getSystemUserById(id);
        return ResponseEntity.ok(systemUser);
    }

    public ResponseEntity<SystemUser> updateSystemUser(@PathVariable Long id,
                                                       @RequestBody SystemUser systemUser){
        systemUser = systemUserService.updateSystemUser(id, systemUser);
        return ResponseEntity.ok(systemUser);

    }

}
