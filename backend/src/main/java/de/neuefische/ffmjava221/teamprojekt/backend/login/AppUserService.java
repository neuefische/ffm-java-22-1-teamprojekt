package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;


    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    public AppUser addUser(AppUser appUser) throws UserAlreadyExistsException {
        if(findByUsername(appUser.username()) != null) {
            throw new UserAlreadyExistsException("User with this name already exists");
        }
        String encodedPassword = SecurityConfig.passwordEncoder.encode(appUser.password());
        AppUser encodedAppUser = appUser.withPassword(encodedPassword).withRegTimeStamp(LocalDateTime.now());
        return appUserRepository.save(encodedAppUser);
    }
}
