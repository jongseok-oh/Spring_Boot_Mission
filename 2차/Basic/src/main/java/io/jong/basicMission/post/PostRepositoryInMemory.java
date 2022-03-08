package io.jong.basicMission.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepositoryInMemory implements PostRepository{
    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryInMemory.class);
    private final Map<String, PostDto> PostMap;
    private String AutoInc = "0";


    public PostRepositoryInMemory() {
        this.PostMap = new HashMap<>();
    }

    @Override
    public boolean save(PostDto postDto) {
        PostMap.put(AutoInc, postDto);
        AutoInc += 1;
        return true;
    }

    @Override
    public Map<String, PostDto> findAll() {
        return this.PostMap;
    }

    @Override
    public PostDto findByKey(String Key) {
        return this.PostMap.get(Key);
    }

    @Override
    public boolean delete(String Key, String PassWord) {
        if(this.PostMap.get(Key) == null) {
            logger.info("Post delete : no key");
            return false;
        }
        if(!this.PostMap.get(Key).getPassWord().equals(PassWord)){ // != String reference 비교
            logger.info("Post delete : encorrect PassWord");
            return false;
        }
        logger.info("Post delete : delete sucess");
        this.PostMap.remove(Key);
        return true;
    }

    @Override
    public boolean update(String Key, PostDto postDto, String PassWord) {
        if(this.PostMap.get(Key) == null) return false;
        if(!this.PostMap.get(Key).getPassWord().equals(PassWord)) return false;

        this.PostMap.put(Key, postDto);
        return true;
    }
}
