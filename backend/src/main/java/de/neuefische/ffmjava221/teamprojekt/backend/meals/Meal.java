package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record Meal(String _id, @NotBlank @Size(min = 3) String name) {
}
