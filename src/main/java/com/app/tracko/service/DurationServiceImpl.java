package com.app.tracko.service;

import com.app.tracko.entity.DurationEntity;
import com.app.tracko.model.Duration;
import com.app.tracko.repository.DurationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DurationServiceImpl implements DurationService {

    @Autowired
    private final DurationRepository durationRepository;

    public DurationServiceImpl(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    @Override
    public Duration createDuration(Duration duration) {
        DurationEntity durationEntity = new DurationEntity();
        BeanUtils.copyProperties(duration,durationEntity);
        durationRepository.save(durationEntity);
        return duration;
    }

    @Override
    public List<Duration> getAlldurations() {
        List<DurationEntity> durationEntities
                = durationRepository.findAll();

        List<Duration> durations = durationEntities
                .stream()
                .map(dur -> new Duration(dur.getDurationId(),
                        dur.getDuration()))
                .collect(Collectors.toList());
        return durations;
    }
}
