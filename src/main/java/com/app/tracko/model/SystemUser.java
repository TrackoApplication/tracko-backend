package com.app.tracko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
