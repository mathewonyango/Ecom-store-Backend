package com.example.demo.Cart;

import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

