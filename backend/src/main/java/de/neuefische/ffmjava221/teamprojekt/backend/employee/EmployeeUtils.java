package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeUtils {

    static String generateUUID() {
        UUID randomID = UUID.randomUUID();
        return randomID.toString();
    }
}
