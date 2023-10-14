package com.app.tracko.service;

import com.app.tracko.model.Duration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DurationService {

    Duration createDuration(Duration duration);

    List<Duration> getAlldurations();
}
