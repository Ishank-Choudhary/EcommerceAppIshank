package com.ecommerce.sb_ecom_project.repository.categoryRepository;


import com.ecommerce.sb_ecom_project.model.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String categoryName);
}
