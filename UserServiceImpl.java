package com.kiitinterveiwPrep.Interview.KIT.Services.impl;

import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.UserDto;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.UserRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UsertoDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow( ()-> new ResourceNotFoundException("User", " id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setPhoto(userDto.getPhoto());
        User updatedUser = this.userRepo.save(user);


        return this.UsertoDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow( ()-> new ResourceNotFoundException("User", " id",userId));
        return this.UsertoDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users =  this.userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(user->this.UsertoDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        user.setPhoto(userDto.getPhoto());

        return user;
    }

    private UserDto UsertoDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
