package com.app.tracko.service;

import com.app.tracko.entity.PeopleEntity;
import com.app.tracko.model.Issue;

import java.util.List;

public interface PeopleService {
    long getPeopleCount();
    public List<PeopleEntity> getAllPeoples();
    PeopleEntity createPeople(PeopleEntity peopleEntity);

}
