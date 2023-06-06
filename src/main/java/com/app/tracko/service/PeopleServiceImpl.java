package com.app.tracko.service;

import com.app.tracko.entity.PeopleEntity;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService{

    private final PeopleRepository peopleRepository;

    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public long getPeopleCount() {
        return peopleRepository.count();
    }

    @Override
    public List<PeopleEntity> getAllPeoples() {
        return peopleRepository.findAll();
    }

    @Override
    public PeopleEntity createPeople(@RequestBody PeopleEntity peopleEntity) {
         peopleRepository.save(peopleEntity);
         return peopleEntity;

    }

}
