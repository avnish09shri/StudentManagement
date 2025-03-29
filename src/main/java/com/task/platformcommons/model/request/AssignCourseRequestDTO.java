package com.task.platformcommons.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignCourseRequestDTO {
    private String studentCode;
    private List<Long> courseIds;
}
