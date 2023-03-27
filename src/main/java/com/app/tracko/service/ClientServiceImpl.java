package com.app.tracko.service;

import com.app.tracko.entity.ClientEntity;
import com.app.tracko.model.Client;
import com.app.tracko.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
     public ClientRepository clientRepository;

     public ClientServiceImpl(ClientRepository clientRepository){
         this.clientRepository=clientRepository;
     }
    @Override
    public Client createClient(Client client) {
        ClientEntity clientEntity=null;
        BeanUtils.copyProperties(client, clientEntity);
        clientRepository.save(clientEntity);
        return client;


    }
}
