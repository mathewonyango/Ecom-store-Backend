package com.example.demo.user;

import java.util.Optional;

public class AuthResponse {

//        private final Optional<User> userExists;
        private final String message;
        private final Optional<User> user; // Assuming User is the type of your user entity

        public AuthResponse(String message, Optional<User> user) {
//            this.userExists = userExists;
            this.message = message;
            this.user = user;
        }

//    public  isUserExists() {
//        return userExists;
//    }

    public String getMessage() {
        return message;
    }

    public Optional<User> getUser() {
        return user;
    }
}
