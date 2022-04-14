package com.jongking.jpa.service;

import com.jongking.jpa.dto.UserDto;
import com.jongking.jpa.entity.UserEntity;
import com.jongking.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(
            @Autowired UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    public void createUser(UserDto dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userRepository.save(userEntity);
    }

    public UserDto readUser(Long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        return new UserDto(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getPassword()
        );
    }

    public List<UserDto> readUserAll(){
        List<UserDto> userDtoList = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDtoList.add(
                new UserDto(
                        userEntity.getId(),
                        userEntity.getUserName(),
                        userEntity.getPassword()
                )
        ));
        return userDtoList;
    }

    public void updateUser(Long id, UserDto dto){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null ? userEntity.getPassword() : dto.getPassword()
        );
        userEntity.setUserName(
                dto.getUserName() == null ? userEntity.getUserName() : dto.getUserName()
        );
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        userRepository.deleteById(id);
    }
}
