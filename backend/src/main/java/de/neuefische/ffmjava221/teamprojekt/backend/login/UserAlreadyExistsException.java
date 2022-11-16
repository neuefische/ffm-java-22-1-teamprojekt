package de.neuefische.ffmjava221.teamprojekt.backend.login;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);

    }
}
