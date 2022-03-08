package io.jong.basicMission.board;
import io.jong.basicMission.post.PostRepository;
import io.jong.basicMission.post.PostRepositoryInMemory;




public class BoardDto {
    private String boardName;
    private PostRepository postRepository;

    public BoardDto() {
        this.postRepository = new PostRepositoryInMemory();
    }

    public BoardDto(String boardName) {
        this.boardName = boardName;
        this.postRepository = new PostRepositoryInMemory();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public PostRepository getPostRepository() {
        return this.postRepository;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardName='" + boardName + '\'' +
                ", postRepository=" + postRepository +
                '}';
    }
}
