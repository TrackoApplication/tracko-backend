package com.app.tracko.controller;


import com.app.tracko.model.Epic;
import com.app.tracko.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
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

}
