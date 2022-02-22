package io.jong.basicMission.post;

import java.util.Map;

public interface PostService {
    void createPost(PostDto postDto);
    Map<String, PostDto> readPostAll();
    PostDto readPost(String Key);
    void updatePost(String Key, PostDto boardDto, String PassWord);
    void deletePost(String Key, PostDto postDto);
}
