package com.jongking.jpa.service;

import com.jongking.jpa.dto.PostDto;
import com.jongking.jpa.entity.BoardEntity;
import com.jongking.jpa.entity.PostEntity;
import com.jongking.jpa.entity.UserEntity;
import com.jongking.jpa.repository.BoardRepository;
import com.jongking.jpa.repository.PostRepository;
import com.jongking.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public PostService(
            @Autowired PostRepository postRepository,
            @Autowired BoardRepository boardRepository,
            @Autowired UserRepository userRepository
            ){
        this.postRepository =postRepository;
        this.boardRepository =boardRepository;
        this.userRepository = userRepository;
    }

    public void createPost(Long boardId, PostDto dto){
        // 해당 board가 존재하는 지
        if(!this.boardRepository.existsById(boardId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 해당 user가 존재하는 지
        if(!this.userRepository.existsById(dto.getUserId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(dto.getContent() == null || dto.getTitle() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        BoardEntity boardEntity = this.boardRepository.findById(boardId).get();
        UserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(dto.getContent());
        postEntity.setTitle(dto.getTitle());
        postEntity.setWriter(userEntity);
        postEntity.setBoardEntity(boardEntity);

        this.postRepository.save(postEntity);
    }

    public PostDto readPost(Long id,Long boardId){
        // 해당 id의 post가 존재하지 않으면
        if(!postRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = postRepository.findById(id).get();
        // 해당 id의 post와 boardId가 일치하지 않으면
        if(!postEntity.getBoardEntity().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getWriter().getId()
        );
    }

    public List<PostDto> readPostAll(Long boardId){
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);
        if(boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = boardEntityOptional.get();
        List<PostDto> postDtoList = new ArrayList<>();
        boardEntity.getPostEntityList().forEach(postEntity -> postDtoList.add(
                new PostDto(
                        postEntity.getId(),
                        postEntity.getTitle(),
                        postEntity.getContent(),
                        postEntity.getWriter().getId()
                )
        ));
        return postDtoList;
    }

    public void updatePost(Long id, Long boardId, PostDto dto){
        // 해당 id의 post가 존재하지 않으면
        if(!postRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = postRepository.findById(id).get();
        // 해당 id의 post와 boardId가 일치하지 않으면
        if(!postEntity.getBoardEntity().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        // 업데이트 하려는 게시글의 작성자가 다를 때
        if(!postEntity.getWriter().getId().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        postEntity.setTitle(
                dto.getTitle() == null ? postEntity.getTitle() : dto.getTitle());
        postEntity.setContent(
                dto.getContent() == null ? postEntity.getContent() : dto.getContent());
        postRepository.save(postEntity);
    }

    public void deletePost(Long boardId, Long id){
        // 해당 id의 post가 존재하지 않으면
        if(!postRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 해당 id의 post와 boardId가 일치하지 않으면
        PostEntity postEntity = postRepository.findById(id).get();
        if(!postEntity.getBoardEntity().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        this.postRepository.deleteById(id);
    }
}
