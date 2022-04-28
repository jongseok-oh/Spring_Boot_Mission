package dev.aquashdw.community.controller;

import dev.aquashdw.community.auth.model.CommunityUserDetailsService;
import dev.aquashdw.community.controller.dto.UserDto;
import dev.aquashdw.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final CommunityUserDetailsService communityUserDetailsService;

    public UserController(UserService userService, CommunityUserDetailsService communityUserDetailsService) {
        this.userService = userService;
        this.communityUserDetailsService = communityUserDetailsService;
    }

    //    @PostMapping
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
//        return ResponseEntity.ok(this.userService.createUser(userDto));
//    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.readUser(id));
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        return ResponseEntity.ok(this.userService.readUserAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        this.userService.updateUser(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // View
    @GetMapping("login")
    public String login(){
        return "login-form";
    }

    @GetMapping("signup")
    public String signUp(){
        return "signup-form";
    }

    @PostMapping("signup")
    public String signUpPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_check") String pCheck,
            @RequestParam(value = "is_shop_owner", defaultValue = "false") Boolean sCheck
    ){
        if(!password.equals(pCheck)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        logger.info(username, password, pCheck, sCheck);
        this.communityUserDetailsService.createUser(username,password,sCheck);
        return "redirect:/user/login";
    }
}
