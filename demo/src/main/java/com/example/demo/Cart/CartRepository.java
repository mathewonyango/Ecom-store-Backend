package com.example.demo.Cart;

import com.example.demo.ProductCategories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository  extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long User_id);


}