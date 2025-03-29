package com.task.platformcommons.utils;

import com.task.platformcommons.model.entity.Address;
import com.task.platformcommons.model.entity.Student;
import com.task.platformcommons.model.request.AddStudentRequestDTO;
import com.task.platformcommons.model.request.AddressDTO;
import com.task.platformcommons.model.response.GetStudentsByCourseResponseDTO;
import com.task.platformcommons.model.response.GetStudentsByNameResponseDTO;
import com.task.platformcommons.model.response.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public Student mapToEntity(AddStudentRequestDTO request, String studentCode) {

        Student student = new Student();
        BeanUtils.copyProperties(request, student);
        student.setStudentCode(studentCode);
        student.setRole("ROLE_STUDENT");

        List<Address> addresses = request.getAddresses().stream().map(dto -> {
            Address address = new Address();
            address.setType(dto.getType());
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            address.setZipCode(dto.getZipCode());

            address.setStudent(student);
            return address;
        }).collect(Collectors.toList());

        student.setAddresses(addresses);
        return student;
    }

    public static StudentResponseDTO mapToResponse(Student student) {
        return StudentResponseDTO.builder().studentCode(student.getStudentCode()).build();
    }



    public GetStudentsByNameResponseDTO toGetStudentsByName(Student student) {

        GetStudentsByNameResponseDTO dto = new GetStudentsByNameResponseDTO();
        BeanUtils.copyProperties(student, dto);

        List<Address> addressList = student.getAddresses().stream().map(address -> {
            Address addr = new Address();
            addr.setId(address.getId());
            addr.setType(address.getType());
            addr.setStreet(address.getStreet());
            addr.setCity(address.getCity());
            addr.setState(address.getState());
            addr.setZipCode(address.getZipCode());
            return addr;
        }).collect(Collectors.toList());

        dto.setAddresses(addressList);
        return dto;
    }

    public GetStudentsByCourseResponseDTO mapToGetStudentsByCourseResponseDTO(Student student) {
        return GetStudentsByCourseResponseDTO.builder()
                .studentCode(student.getStudentCode())
                .name(student.getName())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .addresses(student.getAddresses().stream()
                        .map(this::mapToAddressDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public AddressDTO mapToAddressDTO(Address address) {
        return AddressDTO.builder()
                .type(address.getType())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .build();
    }

    public Address mapToAddressEntity(AddressDTO addressDTO) {
        return Address.builder()
                .type(addressDTO.getType())
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zipCode(addressDTO.getZipCode())
                .build();
    }
}
