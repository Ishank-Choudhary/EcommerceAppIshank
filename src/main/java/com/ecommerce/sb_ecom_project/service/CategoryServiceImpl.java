package com.ecommerce.sb_ecom_project.service;
import com.ecommerce.sb_ecom_project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private static AtomicLong atomicCounter = new AtomicLong();
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        String uniqueID = String.valueOf(atomicCounter.getAndIncrement()) + UUID.randomUUID().toString();
        category.setCategoryId(uniqueID);
        categories.add(category);
    }

    @Override
    public String deleteCategory(String categoryId) {
        Category category = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not found"));
        categories.remove(category);
        return "Category with categoryID "+categoryId + "deleted successfully";
    }
}