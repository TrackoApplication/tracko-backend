package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
        name="SystemUser",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
)
)

@Builder
@AllArgsConstructor
public class SystemUserEntity{
    @Id
    @SequenceGenerator(
            name = "systemUser_sequence",
            sequenceName = "systemUser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "systemUser_sequence"
    )
    private long SystemUserId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String emailId;

    public SystemUserEntity() {

    }


    //  @Column(name = "verification_token")
//  private String verificationToken;


}
