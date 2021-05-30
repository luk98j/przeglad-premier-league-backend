package com.przeglad_premier_league.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.przeglad_premier_league.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.przeglad_premier_league.model.authentication.User;
import com.przeglad_premier_league.config.LoginRequest;
import com.przeglad_premier_league.config.SignupRequest;
import com.przeglad_premier_league.config.JwtResponse;
import com.przeglad_premier_league.config.MessageResponse;
import com.przeglad_premier_league.model.authentication.RoleRepository;
import com.przeglad_premier_league.model.authentication.UserRepository;
import com.przeglad_premier_league.config.JwtUtils;
import com.przeglad_premier_league.config.UserDetailsImpl;
import com.przeglad_premier_league.config.ConfirmationRequest;
import com.przeglad_premier_league.config.EmailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;

    @Autowired
    EmailSender emailSender;

    @Autowired
    SignupService signupService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Nazwa użytkownika zajęta"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Istnieje już użytkownik o podanym adresie e-mail"));
        }
        return ResponseEntity.ok(new MessageResponse(signupService.signup(signUpRequest)));
    }

    @RequestMapping("/confirm")
    public ResponseEntity<?> greeting(@Valid @RequestBody ConfirmationRequest confirmationRequest) {

        User user = userRepository.getUserByConfirmationID(confirmationRequest.getConfirmationID());
        if(user!=null){
            if(!user.isEnabled()){
                user.setEnabled(true);
                user.setConfirmationID(null);
                userRepository.save(user);
                return ResponseEntity.ok(new MessageResponse(user.getUsername() + " aktywowany!"));
            }
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Zły link."));
    }

}