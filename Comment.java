package com.kiitinterveiwPrep.Interview.KIT.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne
    private  Post post;

    @ManyToOne
    private  User user;
}
