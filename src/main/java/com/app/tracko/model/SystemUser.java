package com.app.tracko.model;

import com.app.tracko.entity.AccessGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {

    private long SystemUserId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailId;

}
