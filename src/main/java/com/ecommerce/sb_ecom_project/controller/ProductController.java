package com.ecommerce.sb_ecom_project.controller;

import com.ecommerce.sb_ecom_project.config.AppConstant;
import com.ecommerce.sb_ecom_project.model.Product;
import com.ecommerce.sb_ecom_project.payload.ProductDTO;
import com.ecommerce.sb_ecom_project.payload.ProductResponse;
import com.ecommerce.sb_ecom_project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/getAllProductList")
//    public ResponseEntity<ProductResponse> getAllProduct(
//            @RequestParam(name="pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
//            @RequestParam(name="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
//            @RequestParam(name="sortBy", defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
//            @RequestParam(name="sortOrder",defaultValue = AppConstant.SORT_ORDER,required = false) String sortOrder
//    ){
//        ProductResponse productResponse = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
//        return new ResponseEntity<>(productResponse, HttpStatus.OK);
//    }

    @PostMapping("/{categoryId}/addProduct") // {cateforyId} we are using here so that we can add a product in a particular id
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Integer categoryId){
        ProductDTO createdProduct = productService.addProduct(productDTO, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // .body(createdProduct) will return the newly created product to the user.
    }


}
