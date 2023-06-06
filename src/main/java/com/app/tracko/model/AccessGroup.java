package com.app.tracko.model;

import com.app.tracko.entity.SystemUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessGroup {
    private long accessGroupId;
    private String accessGroupName;


}
