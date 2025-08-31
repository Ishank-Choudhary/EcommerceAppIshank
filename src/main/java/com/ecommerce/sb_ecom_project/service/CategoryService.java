package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO>  getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(int categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
}
