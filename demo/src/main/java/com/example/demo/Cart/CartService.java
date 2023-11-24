package com.example.demo.Cart;

import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Service
public class CartService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    public  CartService (CartRepository cartRepository ,UserRepository userRepository){
        this.cartRepository=cartRepository;
        this.userRepository=userRepository;
    }

    public Cart create (Cart Cart){
        return cartRepository.save(Cart);
    }
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }
public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
}

}
