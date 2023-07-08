package com.app.tracko.service;

import com.app.tracko.entity.PriorityEntity;
import com.app.tracko.model.Priority;
import com.app.tracko.repository.PriorityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityServiceImpl implements PriorityService{
    @Autowired
    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Priority createPriority(Priority priority) {
        PriorityEntity priorityEntity = new PriorityEntity();
        BeanUtils.copyProperties(priority,priorityEntity);
        priorityRepository.save(priorityEntity);
        return priority;
    }

    @Override
    public List<Priority> getAllpriorities() {
        List<PriorityEntity> priorityEntities
                = priorityRepository.findAll();

        List<Priority> priorities = priorityEntities
                .stream()
                .map(pt -> new Priority(pt.getPriorityId(),
                        pt.getPriority()))
                .collect(Collectors.toList());
        return priorities;
    }
}
