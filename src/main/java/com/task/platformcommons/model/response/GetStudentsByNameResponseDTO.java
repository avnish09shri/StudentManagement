package com.task.platformcommons.model.response;

import com.task.platformcommons.model.entity.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentsByNameResponseDTO {
    private String studentCode;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private List<Address> addresses = new ArrayList<>();
}
