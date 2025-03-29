package com.task.platformcommons.exception;

import com.task.platformcommons.exception.model.CourseNotFoundException;
import com.task.platformcommons.exception.model.ErrorResponse;
import com.task.platformcommons.exception.model.StudentNotEnrolledException;
import com.task.platformcommons.exception.model.StudentNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(StudentNotFoundException ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFoundException(CourseNotFoundException ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(StudentNotEnrolledException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotEnrolledException(StudentNotEnrolledException ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(HttpServerErrorException.InternalServerError ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(HttpClientErrorException.Unauthorized ex) {
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .build());
    }


}
