package com.app.tracko.controller;

import com.app.tracko.entity.EpicEntity;
import com.app.tracko.model.Epic;
import com.app.tracko.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EpicController {

    @Autowired
    private final EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

    @PostMapping("/epic")
    public Epic createEpic(@RequestBody Epic epic){
        return epicService.createEpic(epic);
    }

    @GetMapping("/epic")
    public List<EpicEntity>getAllEpics(){
        return epicService.getAllEpics();
    }

    @DeleteMapping("/epic/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEpic(@PathVariable Long id) {
        boolean deleted = false;
        deleted = epicService.deleteEpic(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
}


}