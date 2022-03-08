package com.jongking.jpa;

public class UserDto {
    private String userName;

    public UserDto() {
    }

    public UserDto(String userName) {
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
        return "UserDto{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
