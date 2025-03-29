package com.task.platformcommons.controller.student;

import com.task.platformcommons.exception.model.ErrorResponse;
import com.task.platformcommons.model.request.UpdateStudentProfileRequestDTO;
import com.task.platformcommons.model.response.SearchCourseDTO;
import com.task.platformcommons.service.CourseService;
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
@RequestMapping("/api/students/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Student Controller", description = "Student Controller for Courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/search")
    @Operation(
            summary = "Search Course by Topic and Name",
            description = "Search Course by Topic and Name",
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
    public ResponseEntity<List<SearchCourseDTO>> searchCourses(@RequestParam(required = false) String courseName,
                                                               @RequestParam(required = false) String topic) {
        List<SearchCourseDTO> courses = courseService.searchCourses(courseName, topic);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/leave")
    @Operation(
            summary = "Leave a Course",
            description = "Leave a Course",
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
    public ResponseEntity<String> leaveCourse(@RequestParam String studentCode,
                                              @RequestParam String courseName) {
        String response = courseService.leaveCourse(studentCode, courseName);
        return ResponseEntity.ok(response);
    }
}