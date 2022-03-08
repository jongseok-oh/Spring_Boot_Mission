package com.jongking.jpa;

import com.jongking.jpa.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    public BoardController(
            @Autowired BoardService boardService
    ){
        this.boardService = boardService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@RequestBody BoardDto dto){
        this.boardService.createBoard(dto);
    }// CREATE

    @GetMapping("{id}")
    public BoardDto readBoard(@PathVariable("id") int id)
    {
        return boardService.readBoard(id);
    }// READ

    @GetMapping("")
    public List<BoardDto> readBoardAll(){return this.boardService.readBoardAll();}
    // READ ALL

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBoard(
            @PathVariable("id") int id,
            @RequestBody BoardDto dto
    ){
        this.boardService.updateBoard(id,dto);
    }// UPDATE


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBoard(
            @PathVariable("id") int id
    ){
        this.boardService.deleteBoard(id);
    }// DELETE

}
