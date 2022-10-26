package de.neuefische.ffmjava221.teamprojekt.backend.employee;

public record NewEmployee(
        String name
) {
    public Employee withID(String id) {
        return new Employee(id, name);
    }
}
