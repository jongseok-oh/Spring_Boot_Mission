package com.jongking.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{

    @Id
    private String userName;


    public UserEntity() {
    }

    public UserEntity( String userName) {
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                '}';
    }
}
