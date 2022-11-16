package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
    public AppUser addGuest(@RequestBody AppUser appUser) {
        AppUser newAppUser = appUser.withRole(AppUserRole.GUEST);
        return appUserService.addUser(newAppUser);
    }

    @PostMapping("/employee")
    public AppUser addEmployee(@RequestBody AppUser appUser) {
        AppUser newAppUser = appUser.withRole(AppUserRole.EMPLOYEE);
        return appUserService.addUser(newAppUser);
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


