package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.model.Product;
import com.ecommerce.sb_ecom_project.payload.ProductDTO;
import com.ecommerce.sb_ecom_project.payload.ProductResponse;
import com.ecommerce.sb_ecom_project.repository.CategoryRepository;
import com.ecommerce.sb_ecom_project.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO addProduct(ProductDTO productDTO, Integer categoryId) {
        //Find the category where we want to insert the product, we have to be sure that category with that categoryId exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with id: " + categoryId
                ));
        //Mapping productDTO into the product Entity class
        // You map it into your Entity class so that it can be persisted to DB.
        Product product = modelMapper.map(productDTO,Product.class);
//        The product doesn’t know yet which category it belongs to.
//        By setting this, you are linking the product to the existing category.
//        This ensures the foreign key (category_id) is stored in the Product table.
        product.setCategory(category);
        //save product  to repository
        //Now the product is persisted in DB with the correct foreign key (category_id).
        Product savedProduct = productRepository.save(product);

        //convert Entity -> DTO to return to the client
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

//    @Override
//    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
//        return ProductResponse;
//    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
