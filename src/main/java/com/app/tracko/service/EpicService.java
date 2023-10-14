package com.app.tracko.service;

import com.app.tracko.entity.EpicEntity;
import com.app.tracko.model.Epic;

import java.util.List;

public interface EpicService {

    Epic createEpic(Epic epic);

    List<EpicEntity> getAllEpics();

    boolean deleteEpic(Long id);
}