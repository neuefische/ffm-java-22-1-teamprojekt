package de.neuefische.ffmjava221.teamprojekt.backend.employee;

public record UpdateEmployee(
) {
    public static Employee toUpdateTimeStamp(String id, String name, String regTimeStamp) {
        return new Employee(id, name, regTimeStamp);
    }
}
