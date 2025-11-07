package com.ecommerce.sb_ecom_project.model.product.payload.dto;

import com.ecommerce.sb_ecom_project.model.category.payload.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double specialPrice;
    private String imageURL;
    private CategoryDTO category;
}
