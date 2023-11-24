package com.example.demo.Cart;

import lombok.Getter;

@Getter
public class addingProductToCartRequest {
    Long Product_id;
    Long User_id;

    public void setProduct_id(Long product_id) {
        Product_id = product_id;
    }

    public addingProductToCartRequest() {
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }
}
