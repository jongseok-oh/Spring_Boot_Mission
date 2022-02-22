package io.jong.basicMission.post;

public class PostDto {
    private String title;
    private String content;
    private String writer;
    private String passWord;

    public PostDto() {
    }

    public PostDto(String title, String content, String writer, String passWord) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.passWord = passWord;
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

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
