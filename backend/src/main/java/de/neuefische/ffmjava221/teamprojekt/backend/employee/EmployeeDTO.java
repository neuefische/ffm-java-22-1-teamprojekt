package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

public record EmployeeDTO(
        @Id
        String id,

        @NotBlank
        String name
)
 {
    public static Employee toUpdateTimeStamp(String id, String name, String regTimeStamp) {
        return new Employee(id, name, regTimeStamp);
    }
}
