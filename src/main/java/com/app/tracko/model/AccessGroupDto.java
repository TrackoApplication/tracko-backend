package com.app.tracko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroupDto {
    private Long accessGroupId;
    private String accessGroupName;
    private String description;

    private Long noOfMembers;

}
