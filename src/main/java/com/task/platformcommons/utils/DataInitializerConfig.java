package com.task.platformcommons.utils;

import com.task.platformcommons.model.entity.Admin;
import com.task.platformcommons.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializerConfig {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        createUserIfNotExists("admin", "admin123", "ROLE_ADMIN");
    }

    private void createUserIfNotExists(String username, String password, String role) {
        if (!adminRepository.existsByUsername(username)) {
            Admin user = Admin.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .build();
            adminRepository.save(user);

        }
    }
}
