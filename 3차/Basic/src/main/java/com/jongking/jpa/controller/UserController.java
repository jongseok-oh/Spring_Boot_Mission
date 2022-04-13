package com.jongking.jpa.controller;

import dto.UserDto;
import com.jongking.jpa.entity.UserEntity;
import com.jongking.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(
            @Autowired UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @GetMapping("{user}")
    public UserDto readUser(@PathVariable("user") String user){
        Optional<UserEntity> userEntity = this.userRepository.findById(user);

        if(userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity targetEntity = userEntity.get();
        UserDto tempDto = new UserDto(targetEntity.getUserName());
        return tempDto;
    }

    @GetMapping("")
    public List<UserDto> readUserAll(){
        List<UserDto> userDtoList = new ArrayList<>();

        Iterator<UserEntity> EntIter = this.userRepository.findAll().iterator();

        while(EntIter.hasNext()){
            UserEntity userEntity = EntIter.next();
            userDtoList.add(new UserDto(
                    userEntity.getUserName()
            ));
        }
        return userDtoList;
    }

    @PutMapping("{user}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(
            @PathVariable("user") String user,
            @RequestBody UserDto dto
    ){
        logger.info("please be changed.. " + dto.toString());
        Optional<UserEntity> userEntity = this.userRepository.findById(user);
        if(userEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        userEntity.get().setUserName(dto.getUserName());
    }

    @DeleteMapping("{user}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(
            @PathVariable("user") String user
    ){
        logger.info("Why Why!! Please be deleted " + user);
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(user);
        if(userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        this.userRepository.delete(userEntityOptional.get());
    }
}
