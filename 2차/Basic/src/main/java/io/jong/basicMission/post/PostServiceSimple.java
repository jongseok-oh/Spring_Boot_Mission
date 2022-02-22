package io.jong.basicMission.post;

import io.jong.basicMission.board.BoardDto;
import io.jong.basicMission.board.BoardServiceSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostServiceSimple implements PostService{
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceSimple.class);
    private final PostRepository postRepository;

    public PostServiceSimple(@Autowired PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostDto postDto) {
        this.postRepository.save(postDto);
    }

    @Override
    public Map<String, PostDto> readPostAll() {
        return this.postRepository.findAll();
    }

    @Override
    public PostDto readPost(String Key) {
        return this.postRepository.findByKey(Key);
    }

    @Override
    public void updatePost(String Key, PostDto postDto, String PassWord) {
        this.postRepository.update(Key, postDto, PassWord);
    }

    @Override
    public void deletePost(String Key, PostDto postDto) {
        this.postRepository.delete(Key, postDto.getPassWord());
    }
}
