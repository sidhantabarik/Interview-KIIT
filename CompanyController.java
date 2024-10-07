package com.kiitinterveiwPrep.Interview.KIT.Controllers;


import com.kiitinterveiwPrep.Interview.KIT.Payloads.ApiResponse;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.CompanyDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //Create
    @PostMapping("/")
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto){
        CompanyDto createdCategory = this.companyService.createCompany(companyDto);
        return new ResponseEntity<CompanyDto>(createdCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{company_id}")
    public ResponseEntity<CompanyDto> updateCompany(@Valid @RequestBody CompanyDto companyDto, @PathVariable("company_id")Integer company_id){
        CompanyDto updatedCompany = this.companyService.updateCompany(companyDto,company_id);
        return new ResponseEntity<CompanyDto>(updatedCompany,HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{company_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer company_id){
        this.companyService.deleteCompany(company_id);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Company Deleted Successfully",true),HttpStatus.OK);

    }

    // Get
    @GetMapping("/{company_id}")
    public ResponseEntity<CompanyDto> getSingleCompany(@PathVariable Integer company_id){
        CompanyDto companyDto = this.companyService.getCompany(company_id);
        return new ResponseEntity<CompanyDto>(companyDto,HttpStatus.OK);
    }
    // get All
    @GetMapping("/")
    public ResponseEntity<List<CompanyDto>> getAllCompany(){
        List<CompanyDto> companyDtoList = this.companyService.getAllCompany();
        return new ResponseEntity<List<CompanyDto>>(companyDtoList,HttpStatus.OK);
    }
}
