package com.github.syed.bugtracker.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.syed.bugtracker.project.Project;
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
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    @JsonIgnore
    private Project project;

    private Status status;

    @NotNull
    private Priority priority;

}
