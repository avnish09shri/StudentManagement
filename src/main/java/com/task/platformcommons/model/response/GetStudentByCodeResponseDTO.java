package com.task.platformcommons.model.response;

import com.task.platformcommons.model.request.AddressDTO;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentByCodeResponseDTO {
    private String studentCode;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private List<AddressDTO> addresses;
}
