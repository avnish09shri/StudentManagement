package com.task.platformcommons.controller.admin;

import com.task.platformcommons.exception.model.ErrorResponse;
import com.task.platformcommons.model.request.AddCourseRequestDTO;
import com.task.platformcommons.model.response.CourseReponseDTO;
import com.task.platformcommons.service.CourseService;
import com.task.platformcommons.service.impl.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Admin Controller", description = "Admin Controller for Students and Courses")
public class AdminCourseController {

    private final CourseService courseService;

    @PostMapping("courses")
    @Operation(
            summary = "Add New Course",
            description = "Add a New Course for Students",
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
    public ResponseEntity<CourseReponseDTO> addCourse(@RequestBody AddCourseRequestDTO request) {
        return ResponseEntity.ok(courseService.addCourse(request));
    }
}
