package dev.jongking.auth.controller;

import dev.jongking.auth.dto.UserDto;
import dev.jongking.auth.entity.UserEntity;
import dev.jongking.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            @Autowired UserRepository userRepository,
            @Autowired PasswordEncoder passwordEncoder
            ) {
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    @GetMapping("login")
    public String login(){
        return "login-form";
    }

    @GetMapping("signup")
    public String signUp(){
        return "signup-form";
    }

    @PostMapping("signup")
    public String signUpPost(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("password_check") String passwordCheck){
        if (!password.equals(passwordCheck))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(this.passwordEncoder.encode(password));

        userRepository.save(userEntity);
        return "redirect:/home";
    }
}
