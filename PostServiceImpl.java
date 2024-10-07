package com.kiitinterveiwPrep.Interview.KIT.Services.impl;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostResponse;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CategoryRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CompanyRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.PostRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.UserRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId,Integer companyId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User "," id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category "," id",categoryId));
        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company "," id",companyId));
        Post post = this.dtoToPost(postDto);
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post.setCompany(company);
        Post createdPost = this.postRepo.save(post);
        return this.PostToDto(createdPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post "," id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAddDate(new Date());
        Post updatedPost = this.postRepo.save(post);
        return PostToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
           Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id",postId));
           this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allpost = pagePost.getContent();

        List<PostDto> postDtoList = allpost.stream().map(post -> PostToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id",postId));

        return this.PostToDto(post);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id",categoryId));

        Page<Post> pagePost = this.postRepo.findAllByCategory(category,pageable);
        List<Post> postList = pagePost.getContent();

        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {


        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);


        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));

        Page<Post> pagePost = this.postRepo.findAllByUser(user,pageable);
        List<Post> postList = pagePost.getContent();
        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostByCompany(Integer companyId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company"," id",companyId));
        Page<Post> pagePost = this.postRepo.findAllByCompany(company,pageable);
        List<Post> postList = pagePost.getContent();
        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword,Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost =this.postRepo.searchByTitle("%"+keyword+"%",pageable);
        List<Post>  postList =pagePost.getContent();
        List<PostDto> postDtoList= postList.stream().map(post -> PostToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    private Post dtoToPost(PostDto postDto){
        Post post = this.modelMapper.map(postDto,Post.class);
        return post;
    }
    private PostDto PostToDto(Post post){
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }
}
