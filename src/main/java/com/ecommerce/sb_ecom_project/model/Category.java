package com.ecommerce.sb_ecom_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name="categories")
@Data // this will create the getter and setter for all the fields in the class.
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    // Getters and Setters
    @Id                         // the JPA annotation to mark a primary key field in an entity class.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId; //primary key
    @NotBlank(message = "Name cannot be empty")
    @Size(min=4,max=15,message = "Name must be in the range of 4 to 15 characters")
    private String categoryName;
    private String categoryDescription;
    @AssertTrue(message = "Please mark the field as active")
    @JsonProperty("isActive")
    private boolean isActive;
}
