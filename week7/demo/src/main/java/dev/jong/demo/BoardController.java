package dev.jong.demo;

import dev.jong.demo.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
public class BoardController {

    @PostMapping
    public BoardDto createBoard(@RequestBody BoardDto dto){
        return new BoardDto();
    }
}
