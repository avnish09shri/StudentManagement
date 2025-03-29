package com.task.platformcommons.service;

import com.task.platformcommons.model.request.StudentLoginRequest;

public interface StudentAuthenticationService {
    String login(StudentLoginRequest request);

}
