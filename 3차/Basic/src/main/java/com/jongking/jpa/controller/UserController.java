package com.jongking.jpa.controller;

import com.jongking.jpa.aspect.LogArguments;
import com.jongking.jpa.aspect.LogExecutionTime;
import com.jongking.jpa.aspect.LogReturn;
import com.jongking.jpa.service.UserService;
import com.jongking.jpa.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(
            @Autowired UserService userService
    ) {
        this.userService = userService;
    }

    @LogArguments
    @LogExecutionTime
    @LogReturn
    @PostMapping
    public void createUser(@RequestBody UserDto dto){
        this.userService.createUser(dto);
    }// CREATE

    @LogArguments
    @LogExecutionTime
    @LogReturn
    @GetMapping("{id}")
    public UserDto readUser(@PathVariable("id") Long id){
        return this.userService.readUser(id);
    }// READ

    @LogArguments
    @LogExecutionTime
    @LogReturn
    @GetMapping
    public List<UserDto> readUserAll(){
        return this.userService.readUserAll();
    }// READ ALL

    @LogArguments
    @LogExecutionTime
    @LogReturn
    @PutMapping("{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestBody UserDto dto
    ){
        this.userService.updateUser(id,dto);
    }// UPDATE

    @LogArguments
    @LogExecutionTime
    @LogReturn
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }
}
