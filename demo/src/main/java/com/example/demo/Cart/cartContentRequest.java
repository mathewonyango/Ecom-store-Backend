package com.example.demo.Cart;

public class cartContentRequest {
    Long User_id;
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return User_id;
    }

    public cartContentRequest(Long user_id) {
        User_id = user_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }
}
