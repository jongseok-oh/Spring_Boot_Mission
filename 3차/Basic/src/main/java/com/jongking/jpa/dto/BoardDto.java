package com.jongking.jpa.dto;

import javax.validation.constraints.NotBlank;

public class BoardDto {
    private Long board_id;

    @NotBlank
    private String name;

    public BoardDto() {
    }

    public BoardDto(Long board_id, String name) {
        this.board_id = board_id;
        this.name = name;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
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
