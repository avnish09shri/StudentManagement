package com.task.platformcommons.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLoginRequest {
    private String studentCode;
    private LocalDate dateOfBirth;

}
