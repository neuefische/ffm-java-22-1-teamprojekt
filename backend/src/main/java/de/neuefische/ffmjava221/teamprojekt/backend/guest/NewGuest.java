package de.neuefische.ffmjava221.teamprojekt.backend.guest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record NewGuest(

        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",message = "Password must have minimum eight characters, at least one letter and one number!")
        String password,
        String confirmPassword

) {
    public Guest withId(String id) {
        Guest guest = new Guest(firstName(), lastName(), email(), password(), confirmPassword(), id);
        return guest;
    }
}

