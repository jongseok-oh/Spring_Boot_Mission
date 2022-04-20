package dev.jong.demo.service;

import dev.jong.demo.dto.PostDto;
import dev.jong.demo.entity.PostEntity;
import dev.jong.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(
            @Autowired PostRepository postRepository
    ){
        this.postRepository = postRepository;
    }

    public void createPost(PostDto dto){
        this.postRepository.save(
                new PostEntity(
                        dto.getId(),
                        dto.getTitle(),
                        dto.getContent(),
                        dto.getWriter()
                )
        );
    }

    public PostDto readPost(Long id){
          Optional<PostEntity> postEntityOptional = this.postRepository.findById(id);
          if(postEntityOptional.isEmpty())
              throw new ResponseStatusException(HttpStatus.NOT_FOUND);

          PostEntity postEntity = postEntityOptional.get();
          return new PostDto(
                  postEntity.getId(),
                  postEntity.getTitle(),
                  postEntity.getContent(),
                  postEntity.getWriter()
          );
    }

    public List<PostDto> readPostAll(){
        List<PostDto> postDtoList = new ArrayList<>();

        postRepository.findAll().forEach(postEntity -> postDtoList.add(new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getWriter()
        )));
        return postDtoList;
    }

}
