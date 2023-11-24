package com.example.demo.ProductCategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
     private CategoryRepository CategoryRepository;
    public   CategoryService(CategoryRepository CategoryRepository){
        this.CategoryRepository=CategoryRepository;
    }
    public List<Category> getAllCategories(){
        return CategoryRepository.findAll();
    }
    public Category getCategoryById(Long id) {
        Optional<Category> categoryOptional = CategoryRepository.findById(id);
        return categoryOptional.orElse(null); // or handle the case when the category is not found
    }
    public Category Save(CategoryRequest category ){
        Category categoryData= new Category();

        categoryData.setCategoryName(category.getCategoryName());
        categoryData.setCategoryDescription(category.getCategoryDescription());
        return  CategoryRepository.save(categoryData);
    }
    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional = CategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            CategoryRepository.deleteById(id);
        }
    }

public  void updateCategory(Long id,Category UpdatedCategory){
        Optional <Category> CategoryOptional=CategoryRepository.findById(id);

        if(CategoryOptional.isPresent()){
            Category ExistingCategory=CategoryOptional.get();
            ExistingCategory.setCategoryName(UpdatedCategory.getCategoryName());
            ExistingCategory.setCategoryDescription((UpdatedCategory.getCategoryDescription()));
            CategoryRepository.save(ExistingCategory);
        }

}
}
