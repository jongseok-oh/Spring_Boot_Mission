package dev.jong.demo.dto;

public class PostDto {
    private Long id;
    private String title;
    private String Content;
    private String writer;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        Content = content;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Content='" + Content + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
