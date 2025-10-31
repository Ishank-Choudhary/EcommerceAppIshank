package com.ecommerce.sb_ecom_project.service;

import com.ecommerce.sb_ecom_project.exception.APIException;
import com.ecommerce.sb_ecom_project.model.Category;
import com.ecommerce.sb_ecom_project.payload.CategoryDTO;
import com.ecommerce.sb_ecom_project.payload.CategoryResponse;
import com.ecommerce.sb_ecom_project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.sb_ecom_project.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        // 1️⃣ Determine sorting direction based on input (ascending or descending)
        // Sort is a helper class provided by spring data jpa to define the sorting criteria when you fetch data
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // 2️⃣ Create a Pageable object with page number, page size, and sorting info
        //    Pageable is used by Spring Data JPA to fetch a specific "slice" of data
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        // 3️⃣ Fetch a paginated list of Category entities from the repository
        //    The result is a Page<Category>, which contains both the content and pagination metadata
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        // 4️⃣ Extract the list of Category entities (actual data) from the Page object
        List<Category> categories = categoryPage.getContent();

        // 5️⃣ Handle the case when there are no categories in the database
        if (categories.isEmpty()) {
            throw new APIException("There is no category present in the database, please add a category");
        }

        // 6️⃣ Convert Category entities to CategoryDTO objects for API response
        //    ModelMapper automatically maps fields with matching names between Category and CategoryDTO
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        // 7️⃣ Prepare the response DTO
        //    CategoryResponse is a custom DTO that contains the list of CategoryDTOs + pagination info
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);

        // 8️⃣ Set pagination metadata from the Page object into the response DTO
        //    Page<T> provides built-in methods to get page number, page size, total elements, etc.
        categoryResponse.setPageNumber(categoryPage.getNumber());       // Current page index
        categoryResponse.setPageSize(categoryPage.getSize());           // Number of items per page
        categoryResponse.setTotalElements(categoryPage.getTotalElements()); // Total number of items across all pages
        categoryResponse.setTotalPages(categoryPage.getTotalPages());   // Total number of pages
        categoryResponse.setLastPage(categoryPage.isLast());            // Whether this is the last page

        // 9️⃣ Return the fully prepared response DTO to the controller
        return categoryResponse;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        //used to check if the category is already present
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());

        // If description is not null, replace "{categoryName}" placeholder with the actual category name
        // and update the description in categoryDTO
        if(categoryDTO.getCategoryDescription()!=null){
            String desc = categoryDTO.getCategoryDescription().replace("{categoryName}",categoryDTO.getCategoryName());
            categoryDTO.setCategoryDescription(desc);
        }

        //throws exception if the category is already there.
        if (existingCategory.isPresent()) {
            throw new APIException("Category name already exists: " + categoryDTO.getCategoryName()+". Please change the category name");
        }
        //convert DTO -> entity
        Category category = modelMapper.map(categoryDTO, Category.class);

        // Save Entity
        Category savedCategory = categoryRepository.save(category);

        //convert entity back to dto and return
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public String deleteCategory(int categoryId) {
        List<Category> categories = categoryRepository.findAll(); // we can delete using the category id only instead of findALl() we can find that ategory id only
        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));
        categoryRepository.delete(category);
        return "Category with categoryID " + categoryId + " deleted successfully";
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        // 1. Find the existing category from DB (here we are using the Category object because repository does not know the DTO class it only knows entity class)
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryID",categoryId));

        // 2. Update fields of existingCategory with values coming from input `category`
        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
        existingCategory.setMarkActive(categoryDTO.isMarkActive());

        //save the updated entity
        Category savedCategory = categoryRepository.save(existingCategory);

        //converting back to dto
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
