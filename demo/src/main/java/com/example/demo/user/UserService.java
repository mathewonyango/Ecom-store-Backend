package com.example.demo.user;

import com.example.demo.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService<changePassword> {

    private final UserRepository userRepository;
    private final PasswordResetRequestRepository passwordResetRequestRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordResetRequestRepository passwordResetRequestRepository) {
        this.userRepository = userRepository;
        this.passwordResetRequestRepository = passwordResetRequestRepository;
    }

    // Rest of your code
    PasswordHasher passwordHasher = new PasswordHasher();


    public User registerUser(User user) {
        String password = user.getPassword();
        // Validate email (add your email validation logic here)
        String hashedPassword = PasswordHasher.hashPassword(password);

        // Validate and hash the password
        user.setPassword(hashedPassword);

        // Save the user
        return userRepository.save(user);
    }
    public User saveNewPassword(String email, String newPassword) {
        Optional<Object> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            String hashedPassword = PasswordHasher.hashPassword(newPassword);
            user.setPassword(hashedPassword);

            // Save the updated user
            return userRepository.save(user);
        }

        // Handle the case where the user is not found
        // You can throw an exception, return null, or handle it based on your application's requirements
        return null;
    }



    public Optional<Object> FindUserByEmail(String email) {
        // Find the user by email
        return userRepository.findByEmail(email);


        // Check if the provided password matches the stored plain text password

    }



}
