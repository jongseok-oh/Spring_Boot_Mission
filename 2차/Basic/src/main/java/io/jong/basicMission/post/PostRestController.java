package io.jong.basicMission.post;

import io.jong.basicMission.board.BoardDto;
import io.jong.basicMission.board.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("post/{BoardName}")
public class PostRestController {

    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);

    private final PostService postService;
    private final BoardService boardService;

    public PostRestController(@Autowired PostService postService
    , @Autowired BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @PostMapping()
    public void create(@PathVariable("BoardName") String BoardName,
                            @RequestBody PostDto postDto){
        logger.info("create " + postDto.toString());
        this.boardService.readBoard(BoardName).getPostRepository().save(postDto);
    }

    @GetMapping()
    public Map<String, PostDto> readAll(@PathVariable("BoardName") String BoardName){
        logger.info("find all" + BoardName);
        return this.boardService.readBoard(BoardName).getPostRepository().findAll();
    }

    @GetMapping("{Key}")
    public PostDto readOne(@PathVariable("BoardName") String BoardName,
                           @PathVariable("Key") String Key){
        logger.info("read one" + Key);
        return this.boardService.readBoard(BoardName).getPostRepository().findByKey(Key);
    }

    @PutMapping("{Key}")
    public void update(
            @PathVariable("BoardName") String BoardName,
            @PathVariable("Key") String Key,
            @RequestBody PostDto postDto
    ){
        logger.info("update " + Key);
        this.boardService.readBoard(BoardName).getPostRepository().update(Key, postDto, postDto.getPassWord());
    }

    @PostMapping("{Key}")
    public void delete(@PathVariable("BoardName") String BoardName,
                       @PathVariable("Key") String Key,
                       @RequestBody PostDto postDto){
        logger.info("delete " + postDto.toString());
        this.boardService.readBoard(BoardName).getPostRepository().delete(Key, postDto.getPassWord());
    }
}
