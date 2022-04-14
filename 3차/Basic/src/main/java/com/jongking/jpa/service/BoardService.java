package com.jongking.jpa.service;

import com.jongking.jpa.dto.BoardDto;
import com.jongking.jpa.entity.BoardEntity;
import com.jongking.jpa.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final BoardRepository boardRepository;

    public BoardService(
            @Autowired BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }

    public void createBoard(BoardDto dto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(dto.getName());
        this.boardRepository.save(boardEntity);
    }

    public BoardDto readBoard(Long id){
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(id);
        if(boardEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardEntityOptional.get();
        return new BoardDto(
                boardEntity.getId(),
                boardEntity.getName()
        );
    }

    public List<BoardDto> readBoardAll(){
        List<BoardDto> boardDtoList = new ArrayList<>();
        boardRepository.findAll().forEach(boardEntity -> boardDtoList.add(
                new BoardDto(
                        boardEntity.getId(),
                        boardEntity.getName()
                )
        ));
        return boardDtoList;
    }

    public void updateBoard(Long id, BoardDto dto){
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);

        // 해당하는 id의 board가 존재하지 않으면
        if(boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // if(dto.getName() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        // -> 이건 validate로 해결하자
        BoardEntity boardEntity = boardEntityOptional.get();
        boardEntity.setName(dto.getName());
        boardRepository.save(boardEntity);
    }

    public void deleteBoard(Long id){
        Optional<BoardEntity> targetEntity = this.boardRepository.findById(id);
        if(targetEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.boardRepository.deleteById(id);
    }
}
