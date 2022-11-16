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
}
