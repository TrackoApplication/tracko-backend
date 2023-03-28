package com.app.tracko.entity;

import com.app.tracko.entity.AccessGroupEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    @JoinTable(
            name = "user_access_group",
            joinColumns = @JoinColumn(name = "system_user_id"),
            inverseJoinColumns = @JoinColumn(name = "access_group_id")
    )
    private Set<AccessGroupEntity> accessGroups;


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
