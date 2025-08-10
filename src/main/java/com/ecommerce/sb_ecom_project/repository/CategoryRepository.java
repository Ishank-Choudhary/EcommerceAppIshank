package com.ecommerce.sb_ecom_project.repository;


import com.ecommerce.sb_ecom_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
