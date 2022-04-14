package com.jongking.jpa.controller;

import com.jongking.jpa.aspect.LogArguments;
import com.jongking.jpa.aspect.LogExecutionTime;
import com.jongking.jpa.aspect.LogReturn;
import com.jongking.jpa.dto.PostDto;
import com.jongking.jpa.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @LogExecutionTime
    @LogArguments
    @LogReturn
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@Valid @RequestBody PostDto dto,
                          @PathVariable("boardId") Long boardId){
        this.postService.createPost(boardId, dto);
    }// CREATE

    @LogExecutionTime
    @LogReturn
    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") Long id,
                            @PathVariable("boardId") Long boardId){
        return this.postService.readPost(id, boardId);
    }// READ

    @LogExecutionTime
    @LogReturn
    @GetMapping("")
    public List<PostDto> readPostAll(
            @PathVariable("boardId") Long boardId
    ){
        return this.postService.readPostAll(boardId);
    }// READ ALL

    @LogExecutionTime
    @LogReturn
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(
            @PathVariable("id") Long id,
            @PathVariable("boardId") Long boardId,
            @Valid @RequestBody PostDto dto
    ){
        this.postService.updatePost(id, boardId, dto);
    }// UPDATE

    @LogExecutionTime
    @LogReturn
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(
            @PathVariable("id") Long id,
            @PathVariable("boardId") Long boardId
    ){
        this.postService.deletePost(boardId, id);
    }// DELETE
}
