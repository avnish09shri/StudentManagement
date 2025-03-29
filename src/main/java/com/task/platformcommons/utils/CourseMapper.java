package com.task.platformcommons.utils;

import com.task.platformcommons.model.entity.Course;
import com.task.platformcommons.model.response.CourseReponseDTO;
import com.task.platformcommons.model.response.SearchCourseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseReponseDTO convertToDTO(Course course) {
        return CourseReponseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .courseType(course.getCourseType())
                .duration(course.getDuration())
                .topics(course.getTopics())
                .build();
    }

    public SearchCourseDTO mapToSearchCourseDTO(Course course) {
        return SearchCourseDTO.builder()
                .courseName(course.getName())
                .description(course.getDescription())
                .courseType(course.getCourseType())
                .duration(course.getDuration())
                .topics(course.getTopics())
                .build();
    }
}
