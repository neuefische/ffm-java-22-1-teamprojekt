package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.data.annotation.Id;

public record Employee(
        @Id
        String id,
        String name
) {
}
