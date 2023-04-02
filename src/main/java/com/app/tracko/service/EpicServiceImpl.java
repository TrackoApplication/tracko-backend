package com.app.tracko.service;
import com.app.tracko.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpicServiceImpl implements EpicService {


    @Autowired
    private final EpicRepository epicRepository;


    public EpicServiceImpl(EpicRepository epicRepository) {
        this.epicRepository = epicRepository;
    }
}
