package com.kiitinterveiwPrep.Interview.KIT.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private  Integer categoryId;

    @NotEmpty
    @Size(min=4,message = "Title should be At least 4 characters")
    private String categoryTitle;

    @NotEmpty
    @Size(max=200 , message = "Description can't be more than 200 characters")
    private String categoryDescription;
}
