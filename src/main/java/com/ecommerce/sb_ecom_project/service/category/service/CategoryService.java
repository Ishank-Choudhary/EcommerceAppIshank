package com.ecommerce.sb_ecom_project.service.category.service;

import com.ecommerce.sb_ecom_project.model.category.payload.dto.CategoryDTO;
import com.ecommerce.sb_ecom_project.model.category.payload.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(int categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
}
