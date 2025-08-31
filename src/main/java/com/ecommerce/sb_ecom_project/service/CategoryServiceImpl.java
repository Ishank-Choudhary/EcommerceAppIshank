package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.exception.APIException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.payload.CategoryDTO;
import com.ecommerce.sb_ecom_project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("There is no category present in the database, Please add a category");
        }else{
            return categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .toList();
        }
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(categoryDTO.getCategoryDescription()!=null){
            String desc = categoryDTO.getCategoryDescription().replace("{categoryName}",categoryDTO.getCategoryName());
            categoryDTO.setCategoryDescription(desc);
        }
        if (existingCategory.isPresent()) {
            throw new APIException("Category name already exists: " + categoryDTO.getCategoryName()+" Please change the category name");
        }
        //convert DTO -> entity
        Category category = modelMapper.map(categoryDTO, Category.class);

        // Save Entity
        Category savedCategory = categoryRepository.save(category);

        //convert entity back to dto and return
        return modelMapper.map(savedCategory,CategoryDTO.class);
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
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        // 1. Find the existing category from DB
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));

        // 2. Update fields of existingCategory with values coming from input `category`
        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
        existingCategory.setMarkActive(categoryDTO.isMarkActive());

        //save the updated entity
        Category savedCategory = categoryRepository.save(existingCategory);

        //converting back to dto
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
