package com.task.platformcommons.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseReponseDTO {
    private Long id;
    private String name;
    private String description;
    private String courseType;
    private int duration;
    private List<String> topics;
}
