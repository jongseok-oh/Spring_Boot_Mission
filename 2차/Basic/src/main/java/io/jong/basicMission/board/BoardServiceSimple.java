package io.jong.basicMission.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BoardServiceSimple implements BoardService{
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceSimple.class);
    private final BoardRepository boardRepository;

    public BoardServiceSimple(@Autowired BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void createBoard(BoardDto boardDto) {
        this.boardRepository.save(boardDto);
    }

    @Override
    public Map<String, BoardDto> readBoardAll() {
        return this.boardRepository.findAll();
    }

    @Override
    public BoardDto readBoard(String Key) {
        return this.boardRepository.findByKey(Key);
    }

    @Override
    public void updateBoard(String Key, BoardDto boardDto) {
        this.boardRepository.update(Key, boardDto);
    }

    @Override
    public void deleteBoard(String Key) {
        this.boardRepository.delete(Key);
    }
}
