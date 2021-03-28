package com.myproject.myprojec.dto;

public class UserDetailDto {

    private Long id;
    private int age;
    private String address;
    private String phoneNumber;
    private UserDto user;

    public UserDetailDto() {
    }

    public UserDetailDto(Long id, int age, String address, String phoneNumber, UserDto user) {
        this.id = id;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
