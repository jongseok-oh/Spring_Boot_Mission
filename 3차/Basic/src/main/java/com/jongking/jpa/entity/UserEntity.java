package com.jongking.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community_user") // db에서 user는 예약어로 많이 쓰임 user 사용은 지향 해야함
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPost;

    public UserEntity() {
    }

    public UserEntity(Long id, String userName, String password, List<PostEntity> writtenPost) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.writtenPost = writtenPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getWrittenPost() {
        return writtenPost;
    }

    public void setWrittenPost(List<PostEntity> writtenPost) {
        this.writtenPost = writtenPost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", writtenPost=" + writtenPost +
                '}';
    }
}
