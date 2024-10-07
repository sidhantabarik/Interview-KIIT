package com.kiitinterveiwPrep.Interview.KIT.Controllers;

import com.kiitinterveiwPrep.Interview.KIT.Config.AppConstants;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.ApiResponse;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostResponse;
import com.kiitinterveiwPrep.Interview.KIT.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
     private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/company/{companyId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid  @RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId, @PathVariable Integer companyId){

           PostDto createPostDto=  this.postService.createPost(postDto,userId,categoryId,companyId);
           return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
    }
    // get by User
    @GetMapping("/user/{userId}/posts")
    public  ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                        @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                        @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                        @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir){

        PostResponse postDtoList = this.postService.getPostsByUser(userId,pageNumber,pageSize,sortBy,sortDir);
        return  ResponseEntity.ok(postDtoList);
    }
    //get by Category
    @GetMapping("/category/{categoryId}/posts")
    public  ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                            @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir){
        PostResponse postDtoList = this.postService.getPostsByCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return  ResponseEntity.ok(postDtoList);
    }
    //get by Company
    @GetMapping("/company/{companyId}/posts")
    public  ResponseEntity<PostResponse> getPostsByCompany(@PathVariable Integer companyId,
                                                           @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                           @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                           @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir){
        PostResponse postDtoList = this.postService.getPostByCompany(companyId,pageNumber,pageSize,sortBy,sortDir);
        return  ResponseEntity.ok(postDtoList);
    }
    // Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                    @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                    @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir){

        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }

    // Get Post Details By id
    @GetMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return  ResponseEntity.ok(postDto);
    }
    // Delete

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto,postId);

        return ResponseEntity.ok(updatedPost);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public  ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords,
                                                            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                            @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir){
        List<PostDto> searchResult = this.postService.searchPosts(keywords,pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(searchResult);
    }


}
