package com.example.demo.product.Controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.Cart.Cart;
import com.example.demo.Cart.CartRepository;
import com.example.demo.Cart.addingProductToCartRequest;
import com.example.demo.product.ProductRequest;
import com.example.demo.product.Repository.ProductRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomErrorResponse;
import com.example.demo.product.Product;
import com.example.demo.product.Service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CartRepository CartRepository;
    private final UserService  UserService;

    public ProductController(ProductService productService, ProductRepository productRepository, com.example.demo.Cart.CartRepository cartRepository, com.example.demo.user.UserService userService) {
        this.productService = productService;
        this.productRepository = productRepository;

        CartRepository = cartRepository;
        UserService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);

        return product.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/userCart/{userId}")
    public ResponseEntity<List<Product>> getUserCartProducts(@PathVariable Long userId) {

        Optional<User> user = UserService.findById(userId);
        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart cart = CartRepository.findByUserId(user.get().getId());
        if(cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Product> products = productRepository.findByCartId(cart.getId());

        return ResponseEntity.ok(products);

    }
    private Cart createNewCartForProduct() {
        Cart newCart = new Cart();
        // Set other properties if needed
        return CartRepository.save(newCart);
    }
    @PostMapping("/addProductsToCart")
    public ResponseEntity<String> addProductToCart(addingProductToCartRequest addingProductToCartRequest) {
        Optional<Product> productOptional = productService.getProductById(addingProductToCartRequest.getProduct_id());
        Optional<User> userOptional = UserService.findById(addingProductToCartRequest.getUser_id());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = productOptional.get();
        User user = userOptional.get();

        Cart cart = CartRepository.findByUserId(user.getId());
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart not found for the user");
        }

        // Associate the Product with the Cart
        product.setCart(cart);
        // Save the Product
        productService.save(product);

        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            // Attempt to create the product
            Product createdProduct = productService.createProduct(productRequest);

            // Return a success response with the created product and HTTP status 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            // Log the exception details for debugging
            e.printStackTrace();

            // Handle any exceptions or errors and return an error response with HTTP status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = Optional.ofNullable(productService.updateProduct(id, updatedProduct));

        // Return a 404 (Not Found) response
        return product.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAllProducts() {
        boolean deletionSuccessful = productService.deleteAllProducts();

        if (deletionSuccessful) {
            return ResponseEntity.ok(new CustomErrorResponse(200, "All products have been deleted."));
        } else {
            CustomErrorResponse errorResponse = new CustomErrorResponse(500, "Failed to delete products.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("The product with have been deleted.");

    }

}
