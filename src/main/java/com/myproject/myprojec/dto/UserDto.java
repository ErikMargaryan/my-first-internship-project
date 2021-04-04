package com.myproject.myprojec.dto;

import java.util.List;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private List<UserRatedBookDto> userRatedBookDtoList;
    private UserDetailDto userDetailDto;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String username, String password, List<UserRatedBookDto> userRatedBookDtoList, UserDetailDto userDetailDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRatedBookDtoList = userRatedBookDtoList;
        this.userDetailDto = userDetailDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRatedBookDto> getUsersRatedBooksDtoList() {
        return userRatedBookDtoList;
    }

    public void setUsersRatedBooksDtoList(List<UserRatedBookDto> userRatedBookDtoList) {
        this.userRatedBookDtoList = userRatedBookDtoList;
    }

    public UserDetailDto getUserDetailDto() {
        return userDetailDto;
    }

    public void setUserDetailDto(UserDetailDto userDetailDto) {
        this.userDetailDto = userDetailDto;
    }
}
