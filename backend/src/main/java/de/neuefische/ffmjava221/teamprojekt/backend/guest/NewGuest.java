package de.neuefische.ffmjava221.teamprojekt.backend.guest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record NewGuest(

        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        String password,
        String confirmPassword

) {
    public Guest withId(String id) {
        Guest guest = new Guest(firstName(), lastName(), email(), password(), confirmPassword(), id);
        return guest;
    }
}
