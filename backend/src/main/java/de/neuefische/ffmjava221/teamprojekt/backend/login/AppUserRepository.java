package de.neuefische.ffmjava221.teamprojekt.backend.login;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AppUserRepository extends MongoRepository<AppUser, String> {

    public AppUser findByUsername(String username);
}
