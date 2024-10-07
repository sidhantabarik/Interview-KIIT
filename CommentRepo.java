package com.kiitinterveiwPrep.Interview.KIT.Repositories;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
