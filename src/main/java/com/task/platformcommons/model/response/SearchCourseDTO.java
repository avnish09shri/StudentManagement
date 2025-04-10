package com.task.platformcommons.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCourseDTO {
    private String courseName;
    private String description;
    private String courseType;
    private int duration;
    private List<String> topics;
}
