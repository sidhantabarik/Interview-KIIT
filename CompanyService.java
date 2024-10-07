package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CategoryDto;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CompanyDto;

import java.util.List;

public interface CompanyService {

    //create
    CompanyDto createCompany(CompanyDto companyDto);
    //update
    CompanyDto updateCompany(CompanyDto companyDto,Integer companyId);
    //delete
    void deleteCompany(Integer companyId);
    //get ALL
    List<CompanyDto> getAllCompany();

    //get
    CompanyDto getCompany(Integer companyId);
}
