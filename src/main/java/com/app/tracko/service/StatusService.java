package com.app.tracko.service;

import com.app.tracko.model.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusService {

    Status createState(Status status);

    List<Status> getAllstates();

}
