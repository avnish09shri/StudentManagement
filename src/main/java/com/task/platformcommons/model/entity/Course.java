package com.task.platformcommons.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String courseType;
    private int duration; // Duration in weeks/days/hours

    @ElementCollection
    private List<String> topics;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
