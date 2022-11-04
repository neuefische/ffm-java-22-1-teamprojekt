package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GuestUtils {
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
