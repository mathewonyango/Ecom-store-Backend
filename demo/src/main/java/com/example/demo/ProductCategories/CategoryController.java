package com.example.demo.ProductCategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private  final CategoryService CategoryService;


    private CategoryController(CategoryService CategoryService){
        this.CategoryService=CategoryService;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = CategoryService.getAllCategories();
        if (!categories.isEmpty()) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequest category) {
        Category newCategory = CategoryService.Save(category);
        if (newCategory != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
