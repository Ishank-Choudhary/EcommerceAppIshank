package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category>  getAllCategories();
    void createCategory(Category category);
}
