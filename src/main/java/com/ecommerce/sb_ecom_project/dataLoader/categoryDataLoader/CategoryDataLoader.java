package com.ecommerce.sb_ecom_project.dataLoader.categoryDataLoader;

import com.ecommerce.sb_ecom_project.model.category.entity.Category;
import com.ecommerce.sb_ecom_project.repository.categoryRepository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1) // to ensure it will run before the ProductDataLoader
public class CategoryDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only add default categories if the table is empty
        if(categoryRepository.count() == 0) {
            Category electronics = new Category("Electronics", "Devices and gadgets", true);
            Category books = new Category("Books", "All kinds of books", true);
            Category fashion = new Category("Fashion", "Clothing and accessories", true);
            Category grocery = new Category("Grocery", "Daily essentials", true);
            Category beauty = new Category("Beauty & Health", "Personal care products", true);

            categoryRepository.saveAll(List.of(electronics, books, fashion, grocery, beauty));
            System.out.println("Default categories loaded into DB.");
        }
    }
}
