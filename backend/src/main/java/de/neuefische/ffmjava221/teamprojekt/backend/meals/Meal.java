package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import lombok.With;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@With
public record Meal(String id, @NotBlank @Size(min = 3) String name) {
}
