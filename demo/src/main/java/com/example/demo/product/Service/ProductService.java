package com.example.demo.product.Service;

import com.example.demo.product.Product;
import com.example.demo.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
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


public boolean deleteAllProducts(){
        productRepository.deleteAll();
    return false;
}
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
