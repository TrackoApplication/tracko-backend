package com.app.tracko.service;

import com.app.tracko.entity.SprintEntity;
import com.app.tracko.model.Sprint;
import com.app.tracko.repository.SprintRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository sprintRepository;

    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        SprintEntity sprintEntity = new SprintEntity();

        BeanUtils.copyProperties(sprint,sprintEntity);
        sprintRepository.save(sprintEntity);
        return sprint;
    }
}
