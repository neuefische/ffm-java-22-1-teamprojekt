package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class EmployeeUtils {

    public String generateUUID() {
        UUID randomID = UUID.randomUUID();
        return randomID.toString();
    }

    public String generateINSTANT() {
        Instant timeStamp = Instant.now();
        return timeStamp.toString();
    }
}
