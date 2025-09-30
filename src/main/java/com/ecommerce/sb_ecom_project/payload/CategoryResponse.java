package com.ecommerce.sb_ecom_project.payload;
// Purpose: represents the output format of your API.
// describes how categories are returned to clients.

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //used for the fields to define the getter and setter for the below fields
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private List<CategoryDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
