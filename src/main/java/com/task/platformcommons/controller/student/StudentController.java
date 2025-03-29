package com.task.platformcommons.controller.student;

import com.task.platformcommons.model.request.UpdateStudentProfileRequestDTO;
import com.task.platformcommons.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('STUDENT')")
@Tag(name = "Student Controller", description = "Student Controller for Managing Profile")
public class StudentController {



    private final StudentService studentService;

    @PutMapping("/update-profile")
    @Operation(
            summary = "Update Student's Profile",
            description = "Update Student's Profile",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public ResponseEntity<String> updateStudentProfile(@RequestBody UpdateStudentProfileRequestDTO request) {
        String response = studentService.updateStudentProfile(request);
        return ResponseEntity.ok(response);
    }
}