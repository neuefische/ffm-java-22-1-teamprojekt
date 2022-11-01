package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealRepository extends MongoRepository<Meal, String> {
}
