package com.kiitinterveiwPrep.Interview.KIT.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {
    private Integer company_id;
    @NotEmpty
    @Size(min = 3, max = 100,message = "Company Name must be between 3-100 characters")
    private  String company_name;
}
