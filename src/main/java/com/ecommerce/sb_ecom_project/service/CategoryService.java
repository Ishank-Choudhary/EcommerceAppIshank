package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.payload.CategoryDTO;
import com.ecommerce.sb_ecom_project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(int categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
}
