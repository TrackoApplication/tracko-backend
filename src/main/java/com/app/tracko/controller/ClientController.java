package com.app.tracko.controller;

import com.app.tracko.model.Client;
import com.app.tracko.service.ClientService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClientController {

private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
    return clientService.createClient(client);

    }
}
