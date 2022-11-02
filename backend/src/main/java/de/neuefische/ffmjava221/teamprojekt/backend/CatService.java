package de.neuefische.ffmjava221.teamprojekt.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository repo;

    public Cat save(Cat cat) {
        return repo.save(cat);
    }

    public List<Cat> findAll() {
        return repo.findAll();
    }
}
