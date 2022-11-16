package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.With;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@With
public record AppUser(
        String id,
        String username,
        @Pattern(regexp = "^(?=[^A-Z]*+[A-Z])(?=[^a-z]*+[a-z])(?=\\D*+\\d)(?=[^#?!@$ %^&*-]*+[#?!@$ %^&*-]).{8,}$",message = "Password must have minimum eight characters, at least one letter and one number!")
        String password,
        LocalDateTime regTimeStamp,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        AppUserRole role
) {
}
