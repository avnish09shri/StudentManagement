package com.task.platformcommons.model.response;

import com.task.platformcommons.model.request.AddressDTO;
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
public class GetStudentsByCourseResponseDTO {
    private String studentCode;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private List<AddressDTO> addresses;
}
