package com.ecommerce.sb_ecom_project.service.product.service;

import com.ecommerce.sb_ecom_project.model.product.payload.dto.ProductDTO;
import com.ecommerce.sb_ecom_project.model.product.payload.response.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Integer categoryId);
    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductResponse getProductByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,Integer categoryId);
    ProductResponse getProductByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder,String keyword);
    ProductDTO updateProductById(ProductDTO productDTO,Integer productId);
    ProductDTO deleteProductById(Integer productId);
    ProductDTO updateProductImageByProductId(ProductDTO productDTO, Integer productId);
}
