package com.kiitinterveiwPrep.Interview.KIT.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name ="Company")
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer company_id;
    @Column(name = "name")
    private String company_name;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}
