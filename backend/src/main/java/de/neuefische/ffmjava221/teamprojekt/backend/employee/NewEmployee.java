package de.neuefische.ffmjava221.teamprojekt.backend.employee;

public record NewEmployee(
        String name
) {
    public Employee withId(String id) {
        return new Employee(id, name);
    }
}
