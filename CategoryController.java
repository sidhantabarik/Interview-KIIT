package com.kiitinterveiwPrep.Interview.KIT.Controllers;

import com.kiitinterveiwPrep.Interview.KIT.Payloads.ApiResponse;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CategoryDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
       CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
       return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("category_id") Integer cat_id){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,cat_id);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("category_id") Integer cat_id){
        this.categoryService.deleteCategory(cat_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }
    // get
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("category_id") Integer cat_id){

        CategoryDto categoryDto = this.categoryService.getCategory(cat_id);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    // get All

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){

        List<CategoryDto> categoryDtoList= this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtoList);
    }
}
