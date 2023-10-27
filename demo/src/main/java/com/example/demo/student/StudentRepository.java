package com.example.demo.student;

import com.example.demo.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


}

