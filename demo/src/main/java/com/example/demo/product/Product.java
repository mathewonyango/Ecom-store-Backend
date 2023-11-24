package com.example.demo.product;

import com.example.demo.Cart.Cart;
import com.example.demo.ProductCategories.Category;
import com.example.demo.ProductCategories.CategoryRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // Adding Lombok's NoArgsConstructor
@Entity
@Table // Specify the table name
public class Product {

    @Transient // Marking transient to avoid serialization
    private CategoryRepository categoryRepository;

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @Lob
    private String imageBase64;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;

    public void setCategoryId(Long categoryId) {
        if (categoryId != null && categoryRepository != null) {
            this.category = categoryRepository.findById(categoryId).orElse(null);
        }
    }

    // Constructors, getters, and setters...
}
