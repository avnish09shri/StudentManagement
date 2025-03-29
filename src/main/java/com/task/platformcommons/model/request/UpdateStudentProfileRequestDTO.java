package com.task.platformcommons.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentProfileRequestDTO {
    private String studentCode;
    private String email;
    private String mobileNumber;
    private String fatherName;
    private String motherName;
    private List<AddressDTO> addresses;
}
