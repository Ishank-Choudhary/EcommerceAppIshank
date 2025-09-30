package com.ecommerce.sb_ecom_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generated id values
    private long productId; //primary key
    private String productName;
    private String description;
    private double price;
    private double specialPrice;

    @ManyToOne // many products can belong to one category
    @JoinColumn(name = "category_id") // creates a foreign key 'category_id' in the 'product' table
    //That column will store the Category’s ID, linking products to categories.
    private Category category;
}
