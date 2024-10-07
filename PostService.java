package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostResponse;

import java.util.List;

public interface PostService {


    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId,Integer companyId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post

    PostDto getPostById(Integer postId);

    // get all post by category

    PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    // get all post by user
    PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    // get all post by Company

    PostResponse getPostByCompany(Integer companyId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //Search Posts
    List<PostDto> searchPosts(String keyword,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);




}
