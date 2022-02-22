package io.jong.basicMission.board;


import java.util.Map;

public interface BoardRepository {

    boolean save(BoardDto boardDto);
    Map<String, BoardDto> findAll();
    BoardDto findByKey(String Key);

    boolean delete(String Key);
    boolean update(String Key, BoardDto boardDto);
}
