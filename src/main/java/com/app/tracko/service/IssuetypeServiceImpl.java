package com.app.tracko.service;

import com.app.tracko.entity.IssuetypeEntity;
import com.app.tracko.model.Issuetype;
import com.app.tracko.repository.IssuetypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuetypeServiceImpl implements IssuetypeService {

    @Autowired
    private final IssuetypeRepository issuetypeRepository;

    public IssuetypeServiceImpl(IssuetypeRepository issuetypeRepository) {
        this.issuetypeRepository = issuetypeRepository;
    }

    @Override
    public Issuetype createIssuetype(Issuetype issuetype) {
        IssuetypeEntity issuetypeEntity = new IssuetypeEntity();
        BeanUtils.copyProperties(issuetype,issuetypeEntity);
        issuetypeRepository.save(issuetypeEntity);
        return issuetype;
    }

    @Override
    public List<Issuetype> getAllIssuetypes() {
        List<IssuetypeEntity> issuetypeEntities
                = issuetypeRepository.findAll();

        List<Issuetype> issuetypes = issuetypeEntities
                .stream()
                .map(it -> new Issuetype(it.getIssuetypeId(),
                        it.getIssuetypeName()))
                .collect(Collectors.toList());
        return issuetypes;
    }

    @Override
    public Issuetype getIssuetypeById(Long issuetypeId) {
        IssuetypeEntity issuetypeEntity = issuetypeRepository.findById(issuetypeId).get();
        Issuetype issuetype = new Issuetype();
        BeanUtils.copyProperties(issuetypeEntity,issuetype);
        return issuetype;
    }
}
