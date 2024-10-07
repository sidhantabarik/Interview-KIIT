package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Payloads.CommentDto;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto,Integer postId, Integer userId);

    void deleteComment(Integer commentId);
}
