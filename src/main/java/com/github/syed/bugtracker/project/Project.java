package com.github.syed.bugtracker.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.syed.bugtracker.ColorConverter;
import com.github.syed.bugtracker.ColorDeserializer;
import com.github.syed.bugtracker.ColorSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
    @SequenceGenerator(name="project_id_generator", sequenceName = "project_id_seq", allocationSize = 1)
    private Long id;

    @NotEmpty
    private String name;

    @Convert(converter = ColorConverter.class)
    @JsonSerialize(using = ColorSerializer.class)
    @JsonDeserialize(using = ColorDeserializer.class)
    private Color color;
}
