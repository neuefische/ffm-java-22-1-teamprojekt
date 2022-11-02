package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import javax.validation.constraints.NotBlank;

public record NewEmployee(
        @NotBlank
        String name
) {
    public Employee withId(String id) {
        return new Employee(id, name);
    }
}
