package com.task.platformcommons.service.impl;

import com.task.platformcommons.model.entity.Admin;
import com.task.platformcommons.model.request.AdminLoginRequest;
import com.task.platformcommons.repository.AdminRepository;
import com.task.platformcommons.service.AdminAuthenticationService;
import com.task.platformcommons.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(admin.getUsername(), admin.getRole());

    }
}
