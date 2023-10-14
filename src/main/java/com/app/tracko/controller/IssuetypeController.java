package com.app.tracko.controller;

import com.app.tracko.model.Issuetype;
import com.app.tracko.service.IssuetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v4")
public class IssuetypeController {

    @Autowired
    private final IssuetypeService issuetypeService;

    public IssuetypeController(IssuetypeService issuetypeService) {
        this.issuetypeService = issuetypeService;
    }

    @PostMapping("/issuetypes")
    public Issuetype createIssuetype(@RequestBody Issuetype issuetype){
        return issuetypeService.createIssuetype(issuetype);
    }

    @GetMapping("/issuetypes")
    public List<Issuetype> getAllIssuetypes(){
        return issuetypeService.getAllIssuetypes();
    }

    @GetMapping("/issuetypes/{IssuetypeId}")
    public ResponseEntity<Issuetype> getIssuetypeById(@PathVariable Long IssuetypeId) {
        Issuetype issuetype = null;
        issuetype = issuetypeService.getIssuetypeById(IssuetypeId);
        return ResponseEntity.ok(issuetype);
    }
}
