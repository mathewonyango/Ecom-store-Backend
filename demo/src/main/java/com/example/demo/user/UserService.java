package com.example.demo.user;

import com.example.demo.Cart.Cart;
import com.example.demo.Cart.CartRepository;
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
    private final CartRepository CartRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordResetRequestRepository passwordResetRequestRepository, CartRepository CartRepository) {
        this.userRepository = userRepository;
        this.passwordResetRequestRepository = passwordResetRequestRepository;
        this.CartRepository = CartRepository;
    }

    // Rest of your code
    PasswordHasher passwordHasher = new PasswordHasher();


    public User registerUser(UserRequest user) {

        User newUser= new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        String password = user.getPassword();

        // Validate email (add your email validation logic here)
        String hashedPassword = PasswordHasher.hashPassword(password);

        // Validate and hash the password
        user.setPassword(hashedPassword);

        // Save the user
        User savedUser = userRepository.save(newUser);

        // Create a new cart and associate it with the user
        Cart cart = new Cart();
//        cart.setUser(savedUser.getId());
       cart.setUser(savedUser); // Link the user to the cart

        // Save the cart
        CartRepository.save(cart);

        return savedUser;
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
public Optional <User> findById(Long id){
        return userRepository.findById(id);
}


    public void save(User user) {
        userRepository.save(user);
    }
}
