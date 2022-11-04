package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.guest.Guest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping()
    Guest loginData(@RequestBody @Valid LoginData loginData) {
        try {
            return loginService.checkLoginData(loginData);
        } catch(WrongLoginDataException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

