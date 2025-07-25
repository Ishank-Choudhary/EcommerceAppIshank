package com.ecommerce.sb_ecom_project.controller;

import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.service.CategoryService;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId){
        categoryService.deleteCategory(categoryId);
        return "Category removed successfully";
    }

}
