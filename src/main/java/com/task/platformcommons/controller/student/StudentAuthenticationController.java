package com.task.platformcommons.controller.student;

import com.task.platformcommons.exception.model.ErrorResponse;
import com.task.platformcommons.model.request.AdminLoginRequest;
import com.task.platformcommons.model.request.StudentLoginRequest;
import com.task.platformcommons.service.AdminAuthenticationService;
import com.task.platformcommons.service.StudentAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/student")
@RequiredArgsConstructor
@Tag(name = "Student Login Controller", description = "Student Login Controller")

public class StudentAuthenticationController {

    private final StudentAuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Admin Login",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<String> login(@RequestBody StudentLoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
