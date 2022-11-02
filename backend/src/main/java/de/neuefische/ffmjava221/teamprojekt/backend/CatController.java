package de.neuefische.ffmjava221.teamprojekt.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService service;

    @GetMapping
    public List<Cat> findAll() {
        return service.findAll();
    }

    @PutMapping("{id}")
    public Cat save(@RequestBody Cat cat, @PathVariable String id) {
        Cat catWithCorrectId = cat.withId(id);
        return service.save(catWithCorrectId);
    }
}
