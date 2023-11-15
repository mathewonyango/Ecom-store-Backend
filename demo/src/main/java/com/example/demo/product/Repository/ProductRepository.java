package com.example.demo.product.Repository;
//import com.example.demo.student.StudentRepository;
import com.example.demo.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.demo.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
