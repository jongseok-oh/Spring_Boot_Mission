package io.jong.basicMission.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepositoryInMemory implements BoardRepository{
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryInMemory.class);
    private final Map<String, BoardDto> BoardMap;

    public BoardRepositoryInMemory() {
        this.BoardMap = new HashMap<>();
    }

    @Override
    public boolean save(BoardDto boardDto) {

        if(BoardMap.get(boardDto.getBoardName()) != null)
            return false;
        BoardMap.put(boardDto.getBoardName(), boardDto);
        return true;
    }

    @Override
    public Map<String, BoardDto> findAll() {
        return this.BoardMap;
    }

    @Override
    public BoardDto findByKey(String Key) {
        return this.BoardMap.get(Key);
    }

    @Override
    public boolean delete(String Key) {
        this.BoardMap.remove(Key);
        return true;
    }

    @Override
    public boolean update(String Key, BoardDto boardDto) {
        if( this.BoardMap.get(Key) == null) return false;
        this.BoardMap.put(Key, boardDto);
        return true;
    }
}
