package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    String name;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    Project project;

}
