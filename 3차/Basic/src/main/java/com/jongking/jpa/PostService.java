package com.jongking.jpa;

import com.jongking.jpa.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostDao dao;

    public PostService(
            @Autowired PostDao dao
    ) {
        this.dao = dao;
    }

    public void createPost(PostDto dto){
        this.dao.createPost(dto);
    }

    public PostDto readPost(int id){
        PostEntity postEntity = this.dao.readPost(id);
        PostDto postDto = new PostDto(
                Math.toIntExact(postEntity.getId()),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getUserEntity() == null?
                "Who are you?": postEntity.getUserEntity().getUserName(),
                postEntity.getPassWord(),
                postEntity.getBoardEntity() == null ?
                        0: Math.toIntExact(postEntity.getBoardEntity().getId())
        );
        return postDto;
    }

    public List<PostDto> readPostAll(){
        Iterator<PostEntity> iterator = this.dao.ReadPostAll();
        List<PostDto> postDtoList = new ArrayList<>();
        while(iterator.hasNext()){
            PostEntity postEntity = iterator.next();
            postDtoList.add(
                    new PostDto(Math.toIntExact(postEntity.getId()),
                    postEntity.getTitle(),
                    postEntity.getContent(), postEntity.getUserEntity() == null
                            ?"Who are you?": postEntity.getUserEntity().getUserName(),
                    postEntity.getPassWord(),
                    postEntity.getBoardEntity() == null ?
                            0: Math.toIntExact(postEntity.getBoardEntity().getId())
            ));
        }
        return postDtoList;
    }

    public void upadatePost(int id, PostDto dto){
        this.dao.updatePost(id, dto);
    }

    public void deletePost(int id, PostDto dto){
        this.dao.deletePost(id, dto);
    }
}
