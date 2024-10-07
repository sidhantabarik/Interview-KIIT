package com.kiitinterveiwPrep.Interview.KIT.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name ="post_title",length = 100,nullable = false)
    private  String title;

    @Column(length =10000)
    private String content;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}
