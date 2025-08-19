package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.exception.APIException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.sb_ecom_project.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("There is no category present in the database, Please add a category");
        }else{
            return categoryRepository.findAll();
        }
    }

    @Override
    public void createCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (existingCategory.isPresent()) {
            throw new APIException("Category name already exists: " + category.getCategoryName()+" Please change the category name");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(int categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));
        categoryRepository.delete(category);
        return "Category with categoryID " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, int categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));

        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setCategoryDescription(category.getCategoryDescription());
        existingCategory.setActive(category.isActive());

        return categoryRepository.save(existingCategory);
    }
}
