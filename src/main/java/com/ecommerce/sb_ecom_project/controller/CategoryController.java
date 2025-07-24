package com.ecommerce.sb_ecom_project.controller;

import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    //Interface
    private CategoryService categoryService;

    //This constructor is how Spring gives your controller access to the service layer (CategoryService) — without you having to manually instantiate it.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "Category added successfully";
    }

}
