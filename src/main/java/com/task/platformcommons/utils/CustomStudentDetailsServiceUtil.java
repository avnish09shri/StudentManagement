package com.task.platformcommons.utils;

import com.task.platformcommons.model.entity.Admin;
import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.repository.AdminRepository;
import com.task.platformcommons.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomStudentDetailsServiceUtil implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String studentCode) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        return new org.springframework.security.core.userdetails.User(
                student.getStudentCode(),
                "", // No password authentication required
                Collections.singleton(new SimpleGrantedAuthority("STUDENT"))
        );
    }

}
