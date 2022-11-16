package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/login")
    public void login() {

    }
}


// Guest => login  username vorname lastname
// Employee =>login username vorname
// APPUSer =>login username password role

// userName => Guest AppUSer

// USER Guest oder Employee Oder Admin


