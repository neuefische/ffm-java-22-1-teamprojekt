package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record Guest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        String password,
        String confirmPassword,
        String id
) {
}


