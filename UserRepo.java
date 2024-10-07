package com.kiitinterveiwPrep.Interview.KIT.Repositories;

import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
