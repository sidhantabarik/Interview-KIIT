package com.kiitinterveiwPrep.Interview.KIT.Services.impl;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CompanyDto;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CompanyRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = this.dtoToCompany(companyDto);

        Company createdCompany = this.companyRepo.save(company);
        return this.CompanyToDto(createdCompany);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Integer companyId) {

        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company"," id",companyId));
        company.setCompany_name(companyDto.getCompany_name());
        Company updatedCompany = this.companyRepo.save(company);
        return this.CompanyToDto(updatedCompany);
    }

    @Override
    public void deleteCompany(Integer companyId) {
        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company"," id",companyId));
        this.companyRepo.delete(company);
    }

    @Override
    public List<CompanyDto> getAllCompany() {
        List<Company> companyList= this.companyRepo.findAll();
        List<CompanyDto> companyDtoList =  companyList.stream().map(company -> this.CompanyToDto(company)).collect(Collectors.toList());

        return companyDtoList;
    }

    @Override
    public CompanyDto getCompany(Integer companyId) {

        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company"," id",companyId));
        return this.CompanyToDto(company);
    }

    private Company dtoToCompany(CompanyDto companyDto){
        Company company = this.modelMapper.map(companyDto,Company.class);
        return company;
    }
    private  CompanyDto CompanyToDto(Company company){

        CompanyDto companyDto = this.modelMapper.map(company,CompanyDto.class);
        return companyDto;
    }
}
