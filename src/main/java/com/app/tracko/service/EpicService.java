package com.app.tracko.service;

import com.app.tracko.entity.EpicEntity;

public interface EpicService {
    long getIssueCount();


    EpicEntity createEpic(EpicEntity epic);
}
