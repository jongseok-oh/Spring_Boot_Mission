package io.jong.basicMission.post;

import java.util.Map;

public interface PostRepository {

    boolean save(PostDto postDto);
    Map<String, PostDto> findAll();
    PostDto findByKey(String Key);

    boolean delete(String Key, String PassWord);
    boolean update(String Key, PostDto postDto,String PassWord);
}
