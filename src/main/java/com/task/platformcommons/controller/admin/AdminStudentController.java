package com.task.platformcommons.controller.admin;

import com.task.platformcommons.exception.model.ErrorResponse;
import com.task.platformcommons.model.request.AddStudentRequestDTO;
import com.task.platformcommons.model.request.AssignCourseRequestDTO;
import com.task.platformcommons.model.response.GetStudentsByCourseResponseDTO;
import com.task.platformcommons.model.response.GetStudentsByNameResponseDTO;
import com.task.platformcommons.model.response.StudentResponseDTO;
import com.task.platformcommons.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Admin Controller", description = "Admin Controller for Students and Courses")
public class AdminStudentController {

    private final StudentService studentService;

    @PostMapping("students")
    @Operation(
            summary = "Add New Student",
            description = "Add a New Student",
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
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestHeader("Authorization") String authorization,
                                                         @RequestBody AddStudentRequestDTO request) {
        return ResponseEntity.ok(studentService.addStudent(request));
    }

    @GetMapping("students")
    @Operation(
            summary = "Get Students By Name",
            description = "Get Students By Name",
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
    public ResponseEntity<List<GetStudentsByNameResponseDTO>> getStudentsByName(/*@RequestHeader("Authorization") String authorization,*/
                                                                                @RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }

    @PostMapping("/students/assign-courses")
    @Operation(
            summary = "Assign Courses to Students",
            description = "Assign Courses to Students",
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
    public ResponseEntity<String> assignCoursesToStudent(@RequestHeader("Authorization") String authorization,
                                                         @RequestBody AssignCourseRequestDTO request) {
        String response = studentService.assignCoursesToStudent(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/course")
    @Operation(
            summary = "Get Students assigned to Course Name",
            description = "Get Students assigned to Course Name",
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
    public ResponseEntity<List<GetStudentsByCourseResponseDTO>> getStudentsByCourse(@RequestHeader("Authorization") String authorization,
                                                                                    @RequestParam String courseName) {
        List<GetStudentsByCourseResponseDTO> students = studentService.getStudentsByCourseName(courseName);
        return ResponseEntity.ok(students);
    }
}
