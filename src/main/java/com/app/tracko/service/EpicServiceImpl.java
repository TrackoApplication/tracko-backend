package com.app.tracko.service;

import com.app.tracko.repository.EpicRepository;
import org.springframework.stereotype.Service;

@Service
public class EpicServiceImpl implements EpicService{
    private final EpicRepository epicRepository;

    public EpicServiceImpl(EpicRepository epicRepository) {
        this.epicRepository = epicRepository;
    }

    @Override
    public long getIssueCount() {
        return epicRepository.count();
    }
}
