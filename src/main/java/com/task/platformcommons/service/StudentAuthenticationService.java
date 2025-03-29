package com.task.platformcommons.service;

import com.task.platformcommons.model.entity.Admin;
import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.model.request.AdminLoginRequest;
import com.task.platformcommons.model.request.StudentLoginRequest;
import com.task.platformcommons.repository.AdminRepository;
import com.task.platformcommons.repository.StudentRepository;
import com.task.platformcommons.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class StudentAuthenticationService {

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
