package com.app.tracko.service;

import com.app.tracko.model.Priority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriorityService {
    Priority createPriority(Priority priority);

    List<Priority> getAllpriorities();
}
