package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.payload.ProductDTO;
import com.ecommerce.sb_ecom_project.payload.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Integer categoryId);
  // ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDTO getProductById(Long productId);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    void deleteProduct(Long productId);
}
