package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.guest.Guest;
import de.neuefische.ffmjava221.teamprojekt.backend.guest.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final GuestRepository guestRepository;

    public Guest checkLoginData(LoginData loginData) {
        String emailToFindGuest = loginData.email();
        Guest guestToFind = guestRepository.findByEmail(emailToFindGuest);
        if(guestToFind != null && guestToFind.password().equals(loginData.password())) {
            return guestToFind;
        } else throw new WrongLoginDataException("Wrong Email or Password");
    }
}
