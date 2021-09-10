package com.przeglad_premier_league.service;

import com.przeglad_premier_league.config.EmailService;
import com.przeglad_premier_league.config.SignupRequest;
import com.przeglad_premier_league.controller.EmailSender;
import com.przeglad_premier_league.model.authentication.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public String signup(SignupRequest signUpRequest) {

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getLastName(),
                signUpRequest.getCountry(),
                signUpRequest.getBirthDate());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            String role = ERole.ROLE_USER.name();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
 /*           strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
            */
        }

        user.setRoles(roles);
        user.setConfirmationID(createconfirmationID());
        Context context = new Context();
        context.setVariable("header", "Przeglad Premier League");
        context.setVariable("title", "Potwierdzenie rejestracji");
        context.setVariable("description", "Aktywuj konto klikając w poniższy link: " + "http://localhost:3002/confirm/" + user.getConfirmationID());
        String body = templateEngine.process("template", context);

        emailSender.sendEmail(user.getEmail(), "Rejestracja", body);
        userRepository.save(user);

        return "Sprawdź skrzynkę mailową";
    }

    private String createconfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}
