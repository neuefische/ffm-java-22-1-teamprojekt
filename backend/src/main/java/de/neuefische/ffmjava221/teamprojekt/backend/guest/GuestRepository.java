package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestRepository extends MongoRepository<Guest, String> {


    Guest findByEmail(String emailToFind);
}
