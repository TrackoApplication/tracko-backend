package com.app.tracko.service;
import com.app.tracko.entity.EpicEntity;
import com.app.tracko.model.Epic;
import com.app.tracko.repository.EpicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpicServiceImpl implements EpicService {


    @Autowired
    private final EpicRepository epicRepository;


    public EpicServiceImpl(EpicRepository epicRepository) {
        this.epicRepository = epicRepository;
    }

    @Override
    public Epic createEpic(Epic epic) {
        EpicEntity epicEntity =new EpicEntity();
        BeanUtils.copyProperties(epic,epicEntity);
        epicRepository.save(epicEntity);
        return epic;
    }

    @Override
    public List<EpicEntity> getAllEpics() {
        List<EpicEntity> epicEntities=epicRepository.findAll();
        return epicEntities;
    }

    @Override
    public boolean deleteEpic(Long id) {
        EpicEntity epic = epicRepository.findById(id).get();
        epicRepository.delete(epic);
        return true;
}

}