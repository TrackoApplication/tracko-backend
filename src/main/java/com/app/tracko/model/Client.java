package com.app.tracko.model;

import lombok.Data;

@Data
public class Client {
    private long id;
    private String clientName;
    private String contactPerson;
    private String emailId;
    private String description;
}
