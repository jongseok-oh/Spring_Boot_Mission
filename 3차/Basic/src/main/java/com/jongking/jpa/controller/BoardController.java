package com.jongking.jpa.controller;

import com.jongking.jpa.aspect.LogArguments;
import com.jongking.jpa.aspect.LogExecutionTime;
import com.jongking.jpa.aspect.LogReturn;
import com.jongking.jpa.dto.BoardDto;
import com.jongking.jpa.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @LogExecutionTime
    @LogReturn
    @LogArguments
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@Valid @RequestBody BoardDto dto){
        this.boardService.createBoard(dto);
    }// CREATE

    @LogExecutionTime
    @LogReturn
    @GetMapping("{id}")
    public BoardDto readBoard(@PathVariable("id") Long id)
    {
        return this.boardService.readBoard(id);
    }// READ

    @LogExecutionTime
    @LogReturn
    @GetMapping("")
    public List<BoardDto> readBoardAll(){
        return this.boardService.readBoardAll();
    }// READ ALL

    @LogExecutionTime
    @LogReturn
    @LogArguments
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBoard(
            @PathVariable("id") Long id,
            @Valid @RequestBody BoardDto dto
    ){
        this.boardService.updateBoard(id,dto);
    }// UPDATE


    @LogExecutionTime
    @LogReturn
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBoard(
            @PathVariable("id") Long id
    ){
        this.boardService.deleteBoard(id);
    }// DELETE

}
