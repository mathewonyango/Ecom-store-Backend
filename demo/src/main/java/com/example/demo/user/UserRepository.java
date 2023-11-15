package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);

//    boolean existsByEmailOrUsername(String email);

    // You can add custom query methods here if needed
    // For example, findByEmail(String email) will be implemented automatically by Spring Data JPA
}
