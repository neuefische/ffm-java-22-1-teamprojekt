package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import javax.validation.constraints.NotBlank;

public record EmployeeToUpdateDTO(
        String id,

        @NotBlank
        String name
)
 {
    public static Employee toUpdateTimeStamp(String id, String name, String regTimeStamp) {
        return new Employee(id, name, regTimeStamp);
    }
}
