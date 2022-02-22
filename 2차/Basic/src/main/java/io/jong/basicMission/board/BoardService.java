package io.jong.basicMission.board;

import java.util.Map;

public interface BoardService {
    void createBoard(BoardDto boardDto);
    Map<String, BoardDto> readBoardAll();
    BoardDto readBoard(String Key);
    void updateBoard(String Key, BoardDto boardDto);
    void deleteBoard(String Key);
}
