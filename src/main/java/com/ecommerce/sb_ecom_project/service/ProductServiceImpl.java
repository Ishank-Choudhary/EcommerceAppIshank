package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.exception.APIException;
import com.ecommerce.sb_ecom_project.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.model.Product;
import com.ecommerce.sb_ecom_project.payload.CategoryDTO;
import com.ecommerce.sb_ecom_project.payload.ProductDTO;
import com.ecommerce.sb_ecom_project.payload.ProductResponse;
import com.ecommerce.sb_ecom_project.repository.CategoryRepository;
import com.ecommerce.sb_ecom_project.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        // Default sorting field
        if(sortBy == null || sortBy.isEmpty()) {
            sortBy = "productId"; // default
        }

        // Only allow certain fields to avoid JPA errors
        List<String> allowedSortFields = List.of(
                "productId", "productName", "price", "specialPrice", "category.categoryId"
        );

        if(!allowedSortFields.contains(sortBy)) {
            sortBy = "productId"; // fallback
        }
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //preparing the pageable object to get the information
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        //finding all the products according to the pagination
        Page<Product> productPage = productRepository.findAll(pageDetails);
        //fetching all the product content in a list
        List<Product> products = productPage.getContent();

        if(products.isEmpty()){
        throw new APIException("There are no products available please add some products");
        }
        List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);

        // 8️⃣ Set pagination metadata from the Page object into the response DTO
        //    Page<T> provides built-in methods to get page number, page size, total elements, etc.
        productResponse.setPageNumber(productPage.getNumber());       // Current page index
        productResponse.setPageSize(productPage.getSize());           // Number of items per page
        productResponse.setTotalElements(productPage.getTotalElements()); // Total number of items across all pages
        productResponse.setTotalPages(productPage.getTotalPages());   // Total number of pages
        productResponse.setLastPage(productPage.isLast());            // Whether this is the last page

        // 9️⃣ Return the fully prepared response DTO to the controller
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, Integer categoryId) {
        // Default sorting field
        if(sortBy == null || sortBy.isEmpty()) {
            sortBy = "productId"; // default
        }

        // Only allow certain fields to avoid JPA errors
        List<String> allowedSortFields = List.of(
                "productId", "productName", "price", "specialPrice", "category.categoryId"
        );

        if(!allowedSortFields.contains(sortBy)) {
            sortBy = "productId"; // fallback
        }
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with ID "+categoryId+" not found"));
        Page<Product> productPage = productRepository.findByCategory(category,pageable);

        //fetching all the product content in a list
        List<Product> products = productPage.getContent();

        List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);

        // 8️⃣ Set pagination metadata from the Page object into the response DTO
        //    Page<T> provides built-in methods to get page number, page size, total elements, etc.
        productResponse.setPageNumber(productPage.getNumber());       // Current page index
        productResponse.setPageSize(productPage.getSize());           // Number of items per page
        productResponse.setTotalElements(productPage.getTotalElements()); // Total number of items across all pages
        productResponse.setTotalPages(productPage.getTotalPages());   // Total number of pages
        productResponse.setLastPage(productPage.isLast());            // Whether this is the last page

        // 9️⃣ Return the fully prepared response DTO to the controller
        return productResponse;
    }

    @Override
    public ProductResponse getProductByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword){
        if(sortBy==null || sortBy.isEmpty()){
            sortBy="productId";
        }
        // Only allow certain fields to avoid JPA errors
        List<String> allowedSortFields = List.of(
                "productId", "productName", "price", "specialPrice", "category.categoryId"
        );
        if(!allowedSortFields.contains(sortBy)){
            sortBy="productId";
        }
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);

        Page<Product> productPage = productRepository.findByProductNameContainingIgnoreCase(keyword,pageable);
        List<Product> products = productPage.getContent();

        List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);

        // 8️⃣ Set pagination metadata from the Page object into the response DTO
        //    Page<T> provides built-in methods to get page number, page size, total elements, etc.
        productResponse.setPageNumber(productPage.getNumber());       // Current page index
        productResponse.setPageSize(productPage.getSize());           // Number of items per page
        productResponse.setTotalElements(productPage.getTotalElements()); // Total number of items across all pages
        productResponse.setTotalPages(productPage.getTotalPages());   // Total number of pages
        productResponse.setLastPage(productPage.isLast());            // Whether this is the last page

        // 9️⃣ Return the fully prepared response DTO to the controller
        return productResponse;
    }

    public ProductDTO updateProductById(ProductDTO productDTO, Integer productId) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // update fields
        existing.setProductName(productDTO.getProductName());
        existing.setPrice(productDTO.getPrice());
        existing.setSpecialPrice(productDTO.getSpecialPrice());
        existing.setCategory(modelMapper.map(productDTO.getCategory(), Category.class));

        Product savedProduct = productRepository.save(existing);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProductById(Integer productId) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));
        productRepository.delete(existing);
        return modelMapper.map(existing,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImageByProductId(ProductDTO productDTO,Integer productId) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        existing.setImageURL(productDTO.getImageURL());
        existing.setCategory(modelMapper.map(productDTO.getCategory(), Category.class));
    }
}
