package com.ecommerce.sb_ecom_project.payload;


import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/// Purpose: represents the data structure you accept as input or sometimes what you want to expose internally.

@Data // it helps generating getter,setter,toString(), equals() and hashcode() for all the fields in the class.
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean markActive;
}
