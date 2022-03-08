package com.jongking.jpa;

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
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public PostDao(
            @Autowired PostRepository postRepository,
            @Autowired BoardRepository boardRepository,
            @Autowired UserRepository userRepository
            ){
        this.postRepository =postRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public void createPost(PostDto dto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        postEntity.setPassWord(dto.getPassWord());


        Optional<UserEntity> userEntity= userRepository.findById(dto.getWriter());

        // 기존 이용자인지 판단
        if(userEntity.isEmpty())
        {
            UserEntity tempEntity = new UserEntity();
            tempEntity.setUserName(dto.getWriter());
            userRepository.save(tempEntity);
            postEntity.setUserEntity(tempEntity);
        }else postEntity.setUserEntity(userEntity.get());

        // 해당 게시판이 존재하는지 검사
        Optional<BoardEntity> boardEntity = boardRepository.findById(Long.valueOf(dto.getBoardId()));
        if(boardEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        postEntity.setBoardEntity(boardEntity.get());
        this.postRepository.save(postEntity);
    }

    public PostEntity readPost(int id){
        Optional<PostEntity> postEntity = this.postRepository.findById((long) id);
        if(postEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return postEntity.get();
    }

    public Iterator<PostEntity> ReadPostAll(){
        return this.postRepository.findAll().iterator();
    }

    public void updatePost(int id, PostDto dto){
        logger.info(dto.toString());
        // 해당 id의 게시글이 존재하는지 검사
        Optional<PostEntity> targetEntity = this.postRepository.findById(Long.valueOf(id));
        if(targetEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = targetEntity.get();

        // 비밀번호 일치 검사
        if(!postEntity.getPassWord().equals(dto.getPassWord()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        postEntity.setTitle(dto.getTitle() == null? postEntity.getTitle(): dto.getTitle());
        postEntity.setContent(dto.getContent() == null? postEntity.getContent(): dto.getContent());

        // User CREATE 어캐 할지 몰라서 걍 여기다 했습니다 ㅜㅜ
        // 로그인해서 글을 쓰는게 아닌 글 작성과 게시자 생성은 동시에 이루어진다 생각 <<
        if(dto.getWriter() == null){
            postEntity.setUserEntity(postEntity.getUserEntity());
            // 입력 작성자가 없으면 기존의 글의 작성자 그대로 유지
        }else{
            Optional<UserEntity> tempUserEntityOp = this.userRepository.findById(dto.getWriter());
            // 해당 이름의 작성자가 존재하는지 검사
            if(tempUserEntityOp.isEmpty()){
                UserEntity tempEntity = new UserEntity();
                tempEntity.setUserName(dto.getWriter());
                userRepository.save(tempEntity);
                postEntity.setUserEntity(tempEntity);
                // 없으면 입력한 작성자명으로 저장
            }
            else postEntity.setUserEntity(tempUserEntityOp.get());
        }
        this.postRepository.save(postEntity);
    }

    public void deletePost(int id, PostDto dto){
        // 해당
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if(targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // 비밀번호 다르면
        if(!targetEntity.get().getPassWord().equals(dto.getPassWord()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.postRepository.delete(targetEntity.get());
    }
}
