package com.ecommerce.sb_ecom_project.controller;

import com.ecommerce.sb_ecom_project.exception.CategoryNotFoundException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    //Interface
    private CategoryService categoryService;

    //This constructor is how Spring gives your controller access to the service layer (CategoryService) — without you having to manually instantiate it.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@GetMapping("/api/public/categories")
    @RequestMapping(value="/public/categories",method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //@PostMapping("/api/public/categories")
    @RequestMapping(value="/public/categories",method = RequestMethod.POST)
    public String createCategory(@RequestBody Category category){
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new CategoryNotFoundException("Please enter a Category name, it is a mandatory field");
        }
        categoryService.createCategory(category);
        return "Category added successfully";
    }

    //@DeleteMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value="/admin/categories/{categoryId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return  new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/api/admin/updateCategories/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @RequestBody Category category,
            @PathVariable String categoryId){
        try{
            String status = categoryService.updateCategory(category,categoryId);
            return  new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }

    }

}
