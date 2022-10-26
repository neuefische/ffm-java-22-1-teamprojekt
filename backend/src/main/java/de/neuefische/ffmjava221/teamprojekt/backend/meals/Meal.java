package de.neuefische.ffmjava221.teamprojekt.backend.meals;

public record Meal(String id, String name) {

    Meal(String name) {
        this(null, name);
    }

    public Meal withId(String id) {
        return new Meal(id, this.name);
    }

}
