package com.app.tracko.model;

import com.app.tracko.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDto {
    private Long systemUserId;
    private String firstName;
    private String lastName;
    private String emailId;


    private String accessGroup = "Not Assigned";
    @Enumerated(EnumType.STRING)
    private Role role;
}
