package dev.jong.demo;

import dev.jong.demo.dto.PostDto;
import dev.jong.demo.exception.PostNotExistException;
import dev.jong.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private final PostService postService;

    public PostController(
            @Autowired PostService postService
    ){
        this.postService = postService;
    }

    @GetMapping("test-exception")
    public void throwException(){
        throw new PostNotExistException();
    }

    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") Long id){
        return this.postService.readPost(id);
    }

    @GetMapping
    public List<PostDto> readPostAll(){
        return this.postService.readPostAll();
    }


}
