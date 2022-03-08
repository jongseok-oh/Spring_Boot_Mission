package com.jongking.jpa;

import com.jongking.jpa.entity.UserEntity;
import com.jongking.jpa.repository.PostRepository;
import com.jongking.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserDao(
            @Autowired UserRepository userRepository,
            @Autowired PostRepository postRepository
    ){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void createUser(UserDto dto){
        Optional<UserEntity> userEntity = userRepository.findById(dto.getUserName());
        if(userEntity.isEmpty()) {

        }
    }

}
