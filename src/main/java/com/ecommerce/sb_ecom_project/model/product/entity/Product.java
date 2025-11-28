package com.ecommerce.sb_ecom_project.model.product.entity;

import com.ecommerce.sb_ecom_project.model.category.entity.Category;
import com.ecommerce.sb_ecom_project.model.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generated id values
    private Integer productId; //primary key
    @Size(min=3,max=30,message="Product name must be in between 3 and 50 char")
    private String productName;
    @Size(min=10,max = 200,message = "Description must be between 10 and 200")
    private String description;
    @NotNull
    @Positive(message="Price must be greater than zero")
    private double price;
    private double specialPrice;
    @NotBlank(message = "please enter the image path")
    private String imageURL;

    @ManyToOne // many products can belong to one category
    @JoinColumn(name = "category_id") // creates a foreign key 'category_id' in the 'product' table
    //That column will store the Category’s ID, linking products to categories.
    private Category category;

    @ManyToOne
    @JoinColumn(name="seller_id") // this will be mapped to the foreign key of the user table
    private Users user;
}
