package com.ecommerce.sb_ecom_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    // Getters and Setters
    @Id                         // the JPA annotation to mark a primary key field in an entity class.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId; //primary key
    private String categoryName;
    private String categoryDescription;
    private boolean isActive;
}
