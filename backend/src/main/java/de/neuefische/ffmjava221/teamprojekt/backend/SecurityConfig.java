package de.neuefische.ffmjava221.teamprojekt.backend;

import de.neuefische.ffmjava221.teamprojekt.backend.login.AppUser;
import de.neuefische.ffmjava221.teamprojekt.backend.login.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final AppUserService appUserService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder encoder() {
        return passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/api/users/guest"
                        ,"/api/placements"
                        ,"/api/employees"
                        ,"/api/guests"
                        ,"/api/meals").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/users/me"
                    ,"/api/weather/**"
                    ,"/api/placements/**"
                    ,"/api/employees/**"
                    ,"/api/guests/**"
                    ,"/api/meals/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/employee").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET,
                        "/api/users/login"
                    ,"/api/users/role"
                    ,"/api/users/logout").authenticated()
                .anyRequest().denyAll()
                .and().build();
    }


    @Bean
    public UserDetailsManager userDetailsManager() throws UsernameNotFoundException{
        return new UserDetailsManager() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUserFromDB = appUserService.findByUsername(username);
                if(appUserFromDB == null){
                    throw new UsernameNotFoundException("Username " + username + " not found");
                }
                   return User.builder()
                            .username(username)
                           .password(appUserFromDB.password())
                           .roles(appUserFromDB.role().toString())
                           .build();

            }

            @Override
            public void createUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void updateUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void deleteUser(String username) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public boolean userExists(String username) {
                throw new UnsupportedOperationException("You cannot use the custom UserDetailsManager for this action");
            }

        };
    }

}
