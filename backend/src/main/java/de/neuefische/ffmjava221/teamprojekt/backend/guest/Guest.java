package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record Guest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Pattern(regexp ="/\\S+@\\S+\\.\\S+/",message = "Email should be a valid address")
        String email,
        @Pattern(regexp = "^(?=[^A-Z]*+[A-Z])(?=[^a-z]*+[a-z])(?=\\D*+\\d)(?=[^#?!@$ %^&*-]*+[#?!@$ %^&*-]).{8,}$",message = "Password must have minimum eight characters, at least one letter and one number!")
        String password,
        String confirmPassword,
        String id
) {
}


