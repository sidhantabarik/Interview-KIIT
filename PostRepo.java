package com.kiitinterveiwPrep.Interview.KIT.Repositories;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    Page<Post> findAllByUser(User user, Pageable pageable);
    Page<Post> findAllByCategory(Category category, Pageable pageable);
    Page<Post> findAllByCompany(Company company,Pageable pageable);


    @Query("select p from Post p where p.title like :key")
    Page<Post> searchByTitle(@Param("key") String title, Pageable pageable);

}
