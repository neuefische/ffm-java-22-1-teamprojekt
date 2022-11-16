package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;


@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/login")
    public void login() {
    }

    @GetMapping("/me")
    public String me() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    @GetMapping("/role")
    public String getRole() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .toString();
    }

    @PostMapping("/guest")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser addGuest(@RequestBody AppUser appUser) {
        AppUser newAppUser = appUser.withRole(AppUserRole.GUEST);
        return addAppUser(newAppUser);
    }

    @PostMapping("/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser addEmployee(@RequestBody AppUser appUser) {
        AppUser newAppUser = appUser.withRole(AppUserRole.EMPLOYEE);
        return addAppUser(newAppUser);
    }

    private AppUser addAppUser(AppUser newAppUser){
        try {
            return appUserService.addUser(newAppUser);
        } catch (UserAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}


// Guest => login  username vorname lastname
// Employee =>login username vorname
// APPUSer =>login username password role

// userName => Guest AppUSer

// USER Guest oder Employee Oder Admin


