package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get ALL
    List<CategoryDto> getAllCategory();

    //get
    CategoryDto getCategory(Integer categoryId);
}
