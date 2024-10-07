package com.kiitinterveiwPrep.Interview.KIT.Services.impl;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CategoryDto;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CategoryRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.dtoToCategory(categoryDto);
        Category createdCategory = this.categoryRepo.save(category);
        return this.CategoryToDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.CategoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList =  this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> this.CategoryToDto(category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id", categoryId));
        return this.CategoryToDto(category);
    }



    private Category  dtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto,Category.class);
        return category;
    }
    private  CategoryDto CategoryToDto(Category category){

        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
