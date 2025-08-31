package com.ecommerce.sb_ecom_project.controller;

import com.ecommerce.sb_ecom_project.payload.CategoryDTO;
import com.ecommerce.sb_ecom_project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/public/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Category created successfully");
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/api/admin/updateCategories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @RequestBody CategoryDTO categoryDTO,
            @PathVariable int categoryId) {
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }
}
