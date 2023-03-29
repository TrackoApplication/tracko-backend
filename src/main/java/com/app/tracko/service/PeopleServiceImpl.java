package com.app.tracko.service;

import com.app.tracko.repository.PeopleRepository;
import org.springframework.stereotype.Service;

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
}
