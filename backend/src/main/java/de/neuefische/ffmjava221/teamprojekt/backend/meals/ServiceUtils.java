package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ServiceUtils {

    public String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
