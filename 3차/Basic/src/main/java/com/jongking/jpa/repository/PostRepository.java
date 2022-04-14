package com.jongking.jpa.repository;

import com.jongking.jpa.entity.BoardEntity;
import com.jongking.jpa.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    // JPA customize
    List<PostEntity> findAllByWriter(String Writer); // where writer = ?
    List<PostEntity> findAllByWriterAndBoardEntity(String Writer, BoardEntity boardEntity);
    // where writer = ? and board_id = ?
}
