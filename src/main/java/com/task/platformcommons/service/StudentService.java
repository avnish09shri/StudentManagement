package com.task.platformcommons.service;

import com.task.platformcommons.exception.model.CourseNotFoundException;
import com.task.platformcommons.exception.model.StudentNotFoundException;
import com.task.platformcommons.model.entity.Address;
import com.task.platformcommons.model.entity.Course;
import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.model.request.AddStudentRequestDTO;
import com.task.platformcommons.model.request.AssignCourseRequestDTO;
import com.task.platformcommons.model.request.UpdateStudentProfileRequestDTO;
import com.task.platformcommons.model.response.GetStudentsByCourseResponseDTO;
import com.task.platformcommons.model.response.GetStudentsByNameResponseDTO;
import com.task.platformcommons.model.response.StudentResponseDTO;
import com.task.platformcommons.repository.StudentRepository;
import com.task.platformcommons.utils.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.task.platformcommons.utils.StudentMapper.mapToResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final StudentMapper studentMapper;

    public StudentResponseDTO addStudent(AddStudentRequestDTO request) {
        Student student = studentMapper.mapToEntity(request, getStudentCode());
        saveStudent(student);
        return mapToResponse(student);
    }

    public List<GetStudentsByNameResponseDTO> getStudentsByName(String name) {
        List<Student> studentsByName = studentRepository.findByNameContainingIgnoreCase(name);
        return studentsByName.stream().map(studentMapper::toGetStudentsByName)
                .toList();
    }

    public String assignCoursesToStudent(AssignCourseRequestDTO request) {
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with code: "
                        + request.getStudentCode()));

        List<Course> courses = courseService.getCourses(request.getCourseIds());
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No valid courses found for given IDs");
        }
        student.getCourses().addAll(courses);
        saveStudent(student);

        return "Courses assigned successfully to student " + student.getName();
    }

    public List<GetStudentsByCourseResponseDTO> getStudentsByCourseName(String courseName) {
        List<Student> students = studentRepository.findStudentsByCourseName(courseName);

        return students.stream()
                .map(studentMapper::mapToGetStudentsByCourseResponseDTO)
                .collect(Collectors.toList());
    }

    public String updateStudentProfile(UpdateStudentProfileRequestDTO request) {
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with code: " + request.getStudentCode()));

        if (Objects.nonNull(request.getEmail())) student.setEmail(request.getEmail());
        if (Objects.nonNull(request.getMobileNumber())) student.setMobileNumber(request.getMobileNumber());
        if (Objects.nonNull(request.getFatherName())) student.setFatherName(request.getFatherName());
        if (Objects.nonNull(request.getMotherName())) student.setMotherName(request.getMotherName());

        if (Objects.nonNull(request.getAddresses())) {
            student.getAddresses().clear();
            List<Address> updatedAddresses = request.getAddresses().stream()
                    .map(studentMapper::mapToAddressEntity)
                    .peek(address -> address.setStudent(student))
                    .collect(Collectors.toList());
            student.getAddresses().addAll(updatedAddresses);
        }

        saveStudent(student);
        return "Student profile updated successfully for " + student.getName();
    }

    public String getStudentCode() {
        String code = "10000";

        String lastStudentCode = studentRepository.findLastStudentCode();

        if(StringUtils.isNotEmpty(lastStudentCode)) {
            int lastCode = Integer.parseInt(lastStudentCode) + 1;
            return String.valueOf(lastCode);
        }

        return code;
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}
