package de.neuefische.ffmjava221.teamprojekt.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatRepository extends MongoRepository<Cat, String> {
}
