package com.app.tracko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "Team")
@AllArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;
    private String teamName;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "team_fk",
            referencedColumnName = "teamId"
    )
    private List<SprintEntity> teamSprints;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "team_member",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "system_user_id")}
    )
    private List<SystemUserEntity> members;

    private SystemUserEntity scrum

    public void addUserToTeam(SystemUserEntity systemUserEntity){
        if (systemUserEntity != null){
            members.add(systemUserEntity);
        }
    }

    public void addIssuesToTeam(IssueEntity issueEntity){
        if(issueEntity != null){
            teamIssues.add(issueEntity);
        }
    }


    public TeamEntity() {

    }
}
