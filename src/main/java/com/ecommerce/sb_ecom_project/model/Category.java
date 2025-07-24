package com.ecommerce.sb_ecom_project.model;

public class Category {
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean isActive;

    // All-args constructor
    public Category(String categoryId, String categoryName, String categoryDescription, boolean isActive) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.isActive = isActive;
    }

    public Category() {
    }

    // Getters and Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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
