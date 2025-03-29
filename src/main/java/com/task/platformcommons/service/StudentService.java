package com.task.platformcommons.service;

import com.task.platformcommons.model.request.AddStudentRequestDTO;
import com.task.platformcommons.model.request.AssignCourseRequestDTO;
import com.task.platformcommons.model.request.UpdateStudentProfileRequestDTO;
import com.task.platformcommons.model.response.GetStudentsByCourseResponseDTO;
import com.task.platformcommons.model.response.GetStudentsByNameResponseDTO;
import com.task.platformcommons.model.response.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(AddStudentRequestDTO request);
    List<GetStudentsByNameResponseDTO> getStudentsByName(String name);
    String assignCoursesToStudent(AssignCourseRequestDTO request);
    List<GetStudentsByCourseResponseDTO> getStudentsByCourseName(String courseName);
    String updateStudentProfile(UpdateStudentProfileRequestDTO request);
}
