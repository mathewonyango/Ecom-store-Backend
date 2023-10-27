package com.example.demo.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);

        if (existingProduct.isPresent()) {
            updatedProduct.setId(existingProduct.get().getId());
            int index = products.indexOf(existingProduct.get());
            products.set(index, updatedProduct);
            return updatedProduct;
        }
        return null; // Return null if the product to update is not found.
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
