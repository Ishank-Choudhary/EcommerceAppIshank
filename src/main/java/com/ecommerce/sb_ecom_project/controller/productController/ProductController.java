package com.ecommerce.sb_ecom_project.controller.productController;

import com.ecommerce.sb_ecom_project.config.AppConstant;
import com.ecommerce.sb_ecom_project.model.product.payload.dto.ProductDTO;
import com.ecommerce.sb_ecom_project.model.product.payload.response.ProductResponse;
import com.ecommerce.sb_ecom_project.service.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProductList")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_ORDER, required = false) String sortOrder
    ) {
        //here the controller is calling the implementation service class to get the job done.
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/getProductByCategory/{categoryId}")
    public ResponseEntity<ProductResponse> getProductByCategory(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_ORDER, required = false) String sortOrder,
            @PathVariable Integer categoryId) {
        //Now the controller class will call the service layer to bring all the product list
        ProductResponse productResponse = productService.getProductByCategory(pageNumber, pageSize,sortBy, sortOrder,categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PostMapping("/{categoryId}/addProduct")
    // {categoryId} we are using here so that we can add a product in a particular category
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Integer categoryId) {
        ProductDTO createdProduct = productService.addProduct(productDTO, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // .body(createdProduct) will return the newly created product to the user.
    }

    @GetMapping("/getProductByKeyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_ORDER, required = false) String sortOrder,
            @PathVariable String keyword){
        ProductResponse productResponse = productService.getProductByKeyword(pageNumber, pageSize,sortBy, sortOrder,keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/updateProductById/{productId}")
    public ResponseEntity<ProductDTO> updateProductById(
            @PathVariable Integer productId,
            @RequestBody ProductDTO productDTO
    ){
        try{
            ProductDTO updatedProduct = productService.updateProductById(productDTO,productId);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return  new ResponseEntity<>(null,e.getStatusCode());
        }
    }

    @DeleteMapping("/deleteByProductId/{productId}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Integer productId){
        ProductDTO deletedProductDTO = productService.deleteProductById(productId);
        return new ResponseEntity<>(deletedProductDTO,HttpStatus.OK);
    }

    @PutMapping("/updateImage/{productId}")
    public ResponseEntity<ProductDTO> updateProductImageByProductId(@Valid
            @RequestBody ProductDTO productDTO, // because we want to know which product is updated
            @PathVariable Integer productId){
        try{
            ProductDTO updatedProductImage = productService.updateProductImageByProductId(productDTO,productId);
            return new ResponseEntity<>(updatedProductImage, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(null,e.getStatusCode());
        }

    }

}
