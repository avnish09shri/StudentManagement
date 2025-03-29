package com.task.platformcommons.service.impl;

import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.model.request.StudentLoginRequest;
import com.task.platformcommons.repository.StudentRepository;
import com.task.platformcommons.service.StudentAuthenticationService;
import com.task.platformcommons.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentAuthenticationServiceImpl implements StudentAuthenticationService {

    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;

    public String login(StudentLoginRequest request) {
        Student student = studentRepository.findByStudentCode(request.getStudentCode())
                .orElseThrow(() -> new RuntimeException("Invalid studentCode or DOB"));

        if (!student.getDateOfBirth().equals(request.getDateOfBirth())) {
            throw new RuntimeException("Invalid studentCode or DOB");
        }

        return jwtUtil.generateToken(student.getStudentCode(), student.getRole());
    }
}
