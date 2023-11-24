package com.example.demo.product.Service;

import com.example.demo.Cart.Cart;
import com.example.demo.ProductCategories.Category;
import com.example.demo.ProductCategories.CategoryRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRequest;
import com.example.demo.product.Repository.ProductRepository;
import com.example.demo.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
public Optional <Product> getLoggedInUserCartProducts(Long id, User user, Cart cart){

        return productRepository.findById(id);


}
    public Product createProduct(ProductRequest productRequest) {
        // Fetch the Category entity from the CategoryRepository based on categoryId
        Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategoryId());

        // Check if the category is present, otherwise, set it to null
        Category category = categoryOptional.orElse(null);

        // Create a new Product entity
        Product product = new Product();

        // Map fields from ProductRequest to Product (if needed)
        BeanUtils.copyProperties(productRequest, product); // You might need to manually map fields if needed

        // Set the fetched Category in the Product
        product.setCategory(category);

        // Save the Product entity using the repository
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            updatedProduct.setId(existingProduct.get().getId());
            return productRepository.save(updatedProduct);
        }

        // If the product is not found, you can return null or throw an exception
        return null; // or throw a custom exception



    }

    public Optional<Product> addProductToCart(Product product) {
        Cart cart = getOrCreateCartForProduct(product);
        product.setCart(cart);
        return Optional.of(productRepository.save(product));
    }

    private Cart getOrCreateCartForProduct(Product product) {
        // Logic to retrieve or create a cart for the product
        // You need to define this method based on your application's cart handling

        // Example pseudo-code
        // Check if the product already has a cart
        Cart cart = product.getCart();
        if (cart == null) {
            // If no cart is associated, create a new cart or retrieve an existing one
            cart = createOrRetrieveCartForProduct(product);
        }
        return cart;
    }

    private Cart createOrRetrieveCartForProduct(Product product) {
        // Here, you'll implement logic to either create a new cart or retrieve an existing one
        // This logic might involve checking if the product already has a cart, or creating a new one if not
        // This example assumes a simplistic scenario and pseudo-code

        // Check if the product already has a cart
        Cart cart = product.getCart();
        if (cart == null) {
            // If no cart is associated, create a new cart
            cart = new Cart(); // Instantiate a new Cart object
            // You might want to set additional properties of the cart here if needed

            // Associate the product with the newly created cart
            product.setCart(cart);

            // Save the changes to the product in the database
            productRepository.save(product);
        }
        return cart;
    }


    public boolean deleteAllProducts(){
        productRepository.deleteAll();
    return false;
}
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    public Product save(Product product) {
       return productRepository.save(product);
    }
}
