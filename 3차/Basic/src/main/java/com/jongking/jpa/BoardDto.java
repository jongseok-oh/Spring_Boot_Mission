package com.jongking.jpa;

public class BoardDto {
    private int board_id;
    private String name;

    public BoardDto() {
    }

    public BoardDto(int board_id, String name) {
        this.board_id = board_id;
        this.name = name;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "board_id=" + board_id +
                ", title='" + name + '\'' +
                '}';
    }
}
