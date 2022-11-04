package de.neuefische.ffmjava221.teamprojekt.backend.login;

public class WrongLoginDataException extends RuntimeException{
    public WrongLoginDataException(String message) {
        super(message);
    }
}
