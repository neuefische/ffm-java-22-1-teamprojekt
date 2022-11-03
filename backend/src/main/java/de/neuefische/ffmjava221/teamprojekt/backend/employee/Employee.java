package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotBlank;

public record Employee(
        @Id
        String id,
        @NotBlank
        String name,
        String regTimeStamp
) {
}
