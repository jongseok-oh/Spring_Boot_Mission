package io.jong.basicMission.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("board")
public class BoardRestController {
    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService boardService;

    public BoardRestController(@Autowired BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping()
    public void createBoard(@RequestBody BoardDto boardDto){
        logger.info("create " + boardDto.toString());
        this.boardService.createBoard(boardDto);
    }

    @GetMapping()
    public Map<String, BoardDto> readBoardAll(){
        return this.boardService.readBoardAll();
    }

    @GetMapping("{Key}")
    public BoardDto readOne(@PathVariable("Key") String Key){
        return this.boardService.readBoard(Key);
    }

    @PutMapping("{Key}")
    public void updateBoard(
            @PathVariable("Key") String Key,
            @RequestBody BoardDto boardDto
    ){
        this.boardService.updateBoard(Key, boardDto);
    }

    @DeleteMapping("{Key}")
    public void deleteBoard(@PathVariable("Key") String Key){
        this.boardService.deleteBoard(Key);
    }
}
