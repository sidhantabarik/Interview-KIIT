package com.kiitinterveiwPrep.Interview.KIT.Payloads;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Comment;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private  Integer postId;
    @NotEmpty
    @Size(min = 10, message = "Title Must Contain At least 10 characters")
    private String title;
    @NotEmpty
    @Size(min=10,max =10000,message = "Content must be between 10-10000 characters")
    private String content;
    private Date addDate;
    private CategoryDto category;
    private UserDto user;
    private CompanyDto company;

    private Set<CommentDto> comments = new HashSet<>();
}
