package com.ecommerce.sb_ecom_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="categories")
public class Category {
    @Id                         // the JPA annotation to mark a primary key field in an entity class.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId; //primary key
    private String categoryName;
    private String categoryDescription;
    private boolean isActive;

    // All-args constructor
    public Category(int categoryId, String categoryName, String categoryDescription, boolean isActive) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.isActive = isActive;
    }
    //default constructor, godo to have for JPA
    public Category() {
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
