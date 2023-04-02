package com.app.tracko.controller;


import com.app.tracko.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EpicController {

    @Autowired
    private final EpicService epicService;


    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }
}
