package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clients")
public class ClientEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String clientName;
    private String contactPerson;
    private String emailId;
    private String description;
}
