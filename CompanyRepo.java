package com.kiitinterveiwPrep.Interview.KIT.Repositories;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
