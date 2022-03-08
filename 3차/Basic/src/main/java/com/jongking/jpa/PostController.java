package com.jongking.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(
            @Autowired PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto dto,
                          @PathVariable("boardId") int boardId){
        dto.setBoardId(boardId);
        this.postService.createPost(dto);
    }// CREATE

    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") int id){
        return this.postService.readPost(id);
    }
    // READ

    @GetMapping("")
    public List<PostDto> readPostAll(){
        return this.postService.readPostAll();
    }
    // READ ALL

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody PostDto dto
    ){
        this.postService.upadatePost(id, dto);
    }// UPDATE

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("id") int id,
            @RequestBody PostDto dto
    ){
        this.postService.deletePost(id, dto);
    }// DELETE
}
