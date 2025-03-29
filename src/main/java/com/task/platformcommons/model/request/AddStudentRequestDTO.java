package com.task.platformcommons.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddStudentRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private List<AddressDTO> addresses;
}
