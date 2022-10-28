package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public record NewMeal(
        @NotBlank(message = "Please enter a name") @Size(min = 3, message = "String too short") String name) {
}
