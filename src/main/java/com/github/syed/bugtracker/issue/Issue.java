package com.github.syed.bugtracker.issue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_id_generator")
    @SequenceGenerator(name="issue_id_generator", sequenceName = "issue_id_seq", allocationSize = 1)
    private Long id;

    private String issueId;

    @NotEmpty
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="project_id", nullable = false)
    @JsonBackReference
    private Project project;

    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    private User assignedUser;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
