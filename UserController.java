package com.kiitinterveiwPrep.Interview.KIT.Controllers;


import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.ApiResponse;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.UserDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    // POST - Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
         UserDto createUserDto =this.userService.createUser(userDto);
         return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT - Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);

        return ResponseEntity.ok(updatedUser);
    }


    //Delete - delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        //return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
    //GET - get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}









































