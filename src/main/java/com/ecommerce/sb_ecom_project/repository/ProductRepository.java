package com.ecommerce.sb_ecom_project.repository;

import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> { //using Integer for primary key productID
    Page<Product> findByCategory(Category category, Pageable pageable);
    Page<Product> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);
    /*
 ┌────────────────────────────┬───────────────────────────────┬───────────────────────────────┐
 │ Keyword in Method Name     │ Meaning                       │ SQL Equivalent                │
 ├────────────────────────────┼───────────────────────────────┼───────────────────────────────┤
 │ findBy                     │ Start of query method          │ SELECT * FROM                 │
 │ ProductName                │ Field in your entity           │ product_name                  │
 │ Containing                 │ Substring match                │ LIKE %keyword%                │
 │ IgnoreCase                 │ Case-insensitive               │ LOWER(column)                 │
 │ Pageable parameter          │ Adds pagination + sorting      │ LIMIT, OFFSET, ORDER BY        │
 └────────────────────────────┴───────────────────────────────┴───────────────────────────────┘
*/
}
