package com.ecommerce.sb_ecom_project.dataLoader.productLoader;

import com.ecommerce.sb_ecom_project.model.category.entity.Category;
import com.ecommerce.sb_ecom_project.model.product.entity.Product;
import com.ecommerce.sb_ecom_project.repository.categoryRepository.CategoryRepository;
import com.ecommerce.sb_ecom_project.repository.productRepository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Order(2)
public class ProductDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDataLoader(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            // Fetch categories from DB
            Optional<Category> electronicsOpt = categoryRepository.findByCategoryName("Electronics");
            Optional<Category> booksOpt = categoryRepository.findByCategoryName("Books");
            Optional<Category> fashionOpt = categoryRepository.findByCategoryName("Fashion");

            // Only add products if categories exist
            if (electronicsOpt.isPresent() && booksOpt.isPresent() && fashionOpt.isPresent()) {
                Category electronics = electronicsOpt.get();
                Category books = booksOpt.get();
                Category fashion = fashionOpt.get();

                Product laptop = new Product();
                laptop.setProductName("Laptop");
                laptop.setDescription("High performance laptop");
                laptop.setPrice(70000);
                laptop.setSpecialPrice(65000);
                laptop.setImageURL("/images/laptop.jpg");
                laptop.setCategory(electronics);

                Product smartphone = new Product();
                smartphone.setProductName("Smartphone");
                smartphone.setDescription("Latest Android smartphone");
                smartphone.setPrice(30000);
                smartphone.setSpecialPrice(28000);
                smartphone.setImageURL("/images/phone.jpg");
                smartphone.setCategory(electronics);

                Product novel = new Product();
                novel.setProductName("Mystery Novel");
                novel.setDescription("Thrilling mystery book");
                novel.setPrice(500);
                novel.setSpecialPrice(450);
                novel.setImageURL("/images/novel.jpg");
                novel.setCategory(books);

                Product tshirt = new Product();
                tshirt.setProductName("T-Shirt");
                tshirt.setDescription("100% cotton T-shirt");
                tshirt.setPrice(800);
                tshirt.setSpecialPrice(700);
                tshirt.setImageURL("/images/tshirt.jpg");
                tshirt.setCategory(fashion);

                productRepository.saveAll(List.of(laptop, smartphone, novel, tshirt));
                System.out.println("Default products loaded into DB.");
            }
        }
    }
}
