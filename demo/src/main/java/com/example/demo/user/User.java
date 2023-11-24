package com.example.demo.user;

import com.example.demo.Cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;

@Getter


@Entity
@Table(name = "users") // Use a different name for the table

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password; // In a real application, this should be hashed and salted
    private String name;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}



