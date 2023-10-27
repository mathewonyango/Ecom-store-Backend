package com.example.demo.product;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @Lob
    private String imageBase64; // Store the image in base64 format

    public Product() {
    }

    public Product(String name,
                   double price,
                   String imageBase64) {
        this.name = name;
        this.price = price;
        this.imageBase64 = imageBase64;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageBase64='" + imageBase64 + '\'' +
                '}';
    }
// Constructors, getters, and setters...
}
