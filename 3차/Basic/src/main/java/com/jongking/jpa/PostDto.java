package com.jongking.jpa;

public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String passWord;
    private int boardId;

    public PostDto(int id, String title, String content, String writer, String passWord, int boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.passWord = passWord;
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", passWord='" + passWord + '\'' +
                ", boardId=" + boardId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
