package com.jongking.jpa.service;

import dto.BoardDto;
import com.jongking.jpa.dao.BoardDao;
import com.jongking.jpa.entity.BoardEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final BoardDao dao;

    public BoardService(
            @Autowired BoardDao dao
    ){
        this.dao = dao;
    }

    public void createBoard(BoardDto dto){this.dao.createBoard(dto);}

    public BoardDto readBoard(int id){
        BoardEntity boardEntity = this.dao.readBoard(id);
        BoardDto boardDto = new BoardDto(
                Math.toIntExact(boardEntity.getId()),
                boardEntity.getName()
        );
        return boardDto;
    }

    public List<BoardDto> readBoardAll(){
        Iterator<BoardEntity> iterator = this.dao.readBoardAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        while (iterator.hasNext()){
            BoardEntity boardEntity = iterator.next();
            boardDtoList.add(
                    new BoardDto(
                            Math.toIntExact(boardEntity.getId()),
                            boardEntity.getName()
                    )
            );
        }
        return boardDtoList;
    }

    public void updateBoard(int id, BoardDto dto){this.dao.updateBoard(id,dto);}

    public void deleteBoard(int id){this.dao.deleteBoard(id);}
}
