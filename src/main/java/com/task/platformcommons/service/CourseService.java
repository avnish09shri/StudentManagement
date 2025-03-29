package com.task.platformcommons.service;

import com.task.platformcommons.exception.model.CourseNotFoundException;
import com.task.platformcommons.exception.model.StudentNotEnrolledException;
import com.task.platformcommons.exception.model.StudentNotFoundException;
import com.task.platformcommons.model.entity.Course;
import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.model.request.AddCourseRequestDTO;
import com.task.platformcommons.model.response.CourseReponseDTO;
import com.task.platformcommons.model.response.SearchCourseDTO;
import com.task.platformcommons.repository.CourseRepository;
import com.task.platformcommons.repository.StudentRepository;
import com.task.platformcommons.utils.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;

    public CourseReponseDTO addCourse(AddCourseRequestDTO request) {
        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .courseType(request.getCourseType())
                .duration(request.getDuration())
                .topics(request.getTopics())
                .build();

        course = courseRepository.save(course);

        return courseMapper.convertToDTO(course);
    }

    public List<SearchCourseDTO> searchCourses(String courseName, String topic) {
        List<Course> courses;

        if (courseName != null) {
            courses = courseRepository.findByNameContainingIgnoreCase(courseName);
        } else if (topic != null) {
            courses = courseRepository.findCoursesByTopic(topic);
        } else {
            throw new IllegalArgumentException("Either course name or topic must be provided.");
        }

        return courses.stream()
                .map(courseMapper::mapToSearchCourseDTO)
                .collect(Collectors.toList());
    }

    public String leaveCourse(String studentCode, String courseName) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with code: " + studentCode));


        Course course = courseRepository.findByNameContainingIgnoreCase(courseName).stream()
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + courseName));

        if (!student.getCourses().contains(course)) {
            throw new StudentNotEnrolledException("Student is not enrolled in this course.");
        }

        student.getCourses().remove(course);

        studentRepository.save(student);

        return "Successfully left the course: " + courseName;
    }



    public List<Course> getCourses(List<Long> courseIds) {
        return courseRepository.findAllById(courseIds);
    }

}
