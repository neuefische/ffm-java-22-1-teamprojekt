package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.With;

import javax.validation.constraints.*;

@With
public record AppUser(
        String id,
        String username,
        String password,
        String regTimeStamp,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email
) {
}
