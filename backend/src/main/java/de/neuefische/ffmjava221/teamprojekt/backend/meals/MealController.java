package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Meal addMeal(@Valid @RequestBody Meal newMeal) {
        return mealService.saveMeal(newMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable String id,@Valid @RequestBody Meal meal) {
        boolean mealExists = mealService.isMealExisting(id);

        Meal mealToUpdate = meal.withId(id);
        Meal updatedMeal = mealService.saveMeal(mealToUpdate);

        return mealExists ?
                new ResponseEntity<>(updatedMeal, HttpStatus.OK) :
                new ResponseEntity<>(updatedMeal, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable String id) {
        if (mealService.isMealExisting(id)) {
            mealService.deleteMeal(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Meal with ID:"+id+" found");
    }
}
