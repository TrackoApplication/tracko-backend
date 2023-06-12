package com.app.tracko.entity;

import com.app.tracko.entity.AccessGroupEntity;
import com.app.tracko.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@Table(
        name="system_user",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
)
)

@Builder
@AllArgsConstructor
public class SystemUserEntity implements UserDetails {
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
    private long systemUserId;
    private String firstName;
    private String lastName;
    private String password;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String emailId;
    private Boolean isDeleted = false;

    private String accessGroup = "Not Assigned";
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Token> tokens;

    private String resetPasswordToken;

    public SystemUserEntity() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
