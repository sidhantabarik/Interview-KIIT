package com.kiitinterveiwPrep.Interview.KIT.Controllers;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Comment;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.ApiResponse;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CommentDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comments")
     public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer userId,@PathVariable Integer postId){

        CommentDto createComment =  this.commentService.createComment(comment,postId,userId);

        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public  ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment Deleted Successfully",true));
    }

}
