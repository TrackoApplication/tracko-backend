package com.app.tracko.service;

import com.app.tracko.entity.StatusEntity;
import com.app.tracko.model.Status;
import com.app.tracko.repository.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status createState(Status status) {
        StatusEntity statusEntity = new StatusEntity();
        BeanUtils.copyProperties(status,statusEntity);
        statusRepository.save(statusEntity);
        return status;
    }

    @Override
    public List<Status> getAllstates() {
        List<StatusEntity> statusEntities
                = statusRepository.findAll();

        List<Status> statuses = statusEntities
                .stream()
                .map(st -> new Status(st.getStatusId(),
                        st.getStatus()))
                .collect(Collectors.toList());
        return statuses;
    }

}
