package com.task.platformcommons.service;

import com.task.platformcommons.model.request.AdminLoginRequest;

public interface AdminAuthenticationService {
    String login(AdminLoginRequest request);
}
