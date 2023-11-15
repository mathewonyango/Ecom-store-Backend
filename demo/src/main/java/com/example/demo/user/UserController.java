package com.example.demo.user;

import com.example.demo.PasswordHasher;
import com.example.demo.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/users")

public class UserController<UsernameNotFoundException extends Throwable> {
    @Autowired
    private UserService userService;
    private final PasswordResetService passwordResetService;


    @Autowired
    public UserController(UserService userService, PasswordResetService passwordResetService) {
        this.userService = userService;
        this.passwordResetService = passwordResetService;
    }

    PasswordHasher passwordHasher = new PasswordHasher();


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody User authenticationRequest) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();
        PasswordHasher passwordHasher = new PasswordHasher();
        String hashedPassword = PasswordHasher.hashPassword(password);


        Optional<Object> authenticatedUser = userService.FindUserByEmail(email);

        if (authenticatedUser.isPresent()) {
            User user = (User) authenticatedUser.get();
            String dbPassword = user.getPassword();

//            assert hashedPassword != null;
            if (hashedPassword.equals(dbPassword)) {
                // Passwords match, return the authenticated user
                return ResponseEntity.ok(user);
            }
        } else {
            // Handle other cases as needed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"message\": \"The user associated with the  provided email was not found.\"");
        }
        // User not found or passwords don't match

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\"message\": \"Invalid credentials. Please provide valid email and password.\""
        );
    }
    @PostMapping("/password-reset/{Email}/{NewPassword}")
    public ResponseEntity<?> requestPasswordReset(@PathVariable String Email, @PathVariable String NewPassword){

    // Verify user existence, generate token, send Email, persist request
        Optional<User> userExists = userService.FindUserByEmail(Email);

        if (userExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with the provided Email does not exist");
        }
        passwordResetService.savePasswordReset(userExists.get().getEmail(),userExists.get().getName());

        userService.saveNewPassword(Email,NewPassword);

        Optional<User> authenticatedUser = userExists; // Get the authenticated user from wherever you have it
        AuthResponse authResponse = new AuthResponse("The password was changed successfully", authenticatedUser);
        return ResponseEntity.ok(authResponse);
    }




}