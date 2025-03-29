package com.task.platformcommons.service;

import com.task.platformcommons.model.request.AddCourseRequestDTO;
import com.task.platformcommons.model.response.CourseReponseDTO;
import com.task.platformcommons.model.response.SearchCourseDTO;

import java.util.List;

public interface CourseService {
    CourseReponseDTO addCourse(AddCourseRequestDTO request);
    List<SearchCourseDTO> searchCourses(String courseName, String topic);
    String leaveCourse(String studentCode, String courseName);
}
