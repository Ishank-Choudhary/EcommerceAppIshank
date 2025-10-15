package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.payload.ProductDTO;
import com.ecommerce.sb_ecom_project.payload.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Integer categoryId);
    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse getProductByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,Integer categoryId);
    ProductResponse getProductByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,String keyword);
    ProductDTO updateProductById(ProductDTO productDTO,Integer productId);
//    ProductDTO getProductById(Long productId);

//    void deleteProduct(Long productId);
}
