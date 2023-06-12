package com.app.tracko.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAccessGroup {
    private Long accessGroupId;
    private String accessGroupName;
    private List<String> accesses;

}
