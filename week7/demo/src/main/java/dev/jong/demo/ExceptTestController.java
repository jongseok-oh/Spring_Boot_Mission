package dev.jong.demo;

import dev.jong.demo.exception.BaseException;
import dev.jong.demo.exception.ErrorResponseDto;
import dev.jong.demo.exception.PostNotInBoardException;
import dev.jong.demo.exception.PostNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("except")
public class ExceptTestController {
    @GetMapping("{id}")
    public void throwExcpetion(@PathVariable("id") int id){
        switch (id){
            case 1:
                throw new PostNotExistException();
            case 2:
                throw new PostNotInBoardException();
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

//    // 해당 Controller 내부에서만 작동함
//    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponseDto handleBaseException(BaseException exception){
//        return new ErrorResponseDto(exception.getMessage());
//    }
}
