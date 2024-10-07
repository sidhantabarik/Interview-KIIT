package com.kiitinterveiwPrep.Interview.KIT.Payloads;


import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Integer id;

    private String content;

    private UserDto user;
}
