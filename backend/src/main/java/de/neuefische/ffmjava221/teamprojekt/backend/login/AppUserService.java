package de.neuefische.ffmjava221.teamprojekt.backend.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



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
        AppUser encodedAppUser = appUser.withPassword(encodedPassword);
        return appUserRepository.save(encodedAppUser);
    }
}
