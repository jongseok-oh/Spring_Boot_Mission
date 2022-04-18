package dev.jong.demo;

import dev.jong.demo.exception.PostNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {
    @GetMapping("test-exception")
    public void throwException(){
        throw new PostNotExistException();
    }
}
