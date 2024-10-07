package com.kiitinterveiwPrep.Interview.KIT.Services.impl;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Comment;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CommentDto;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CommentRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.PostRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.UserRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id",postId));
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));

        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
            Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment"," id",commentId));

            this.commentRepo.delete(comment);
    }
}
