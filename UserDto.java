package com.kiitinterveiwPrep.Interview.KIT.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username Must Contain At least 4 characters")
    private String name;
    @Email(message = "Email Address is not Valid")
    private String email;
    @NotEmpty
    @Size(min=3,max =10,message = "Passwords must be between 3-10 characters")
    private String password;
    @NotEmpty
    private String about;
    private String photo;
}
