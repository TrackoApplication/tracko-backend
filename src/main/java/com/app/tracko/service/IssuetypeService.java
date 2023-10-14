package com.app.tracko.service;

import com.app.tracko.model.Issuetype;

import java.util.List;

public interface IssuetypeService {
    Issuetype createIssuetype(Issuetype issuetype);

    List<Issuetype> getAllIssuetypes();

    Issuetype getIssuetypeById(Long issuetypeId);
}
