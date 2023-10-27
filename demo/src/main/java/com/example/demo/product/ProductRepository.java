package com.example.demo.product;
//import com.example.demo.student.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
