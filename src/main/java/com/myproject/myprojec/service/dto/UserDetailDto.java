package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.UserDetailEntity;

public class UserDetailDto {

    private Long id;
    private Integer age;
    private String address;
    private String phoneNumber;
    private UserDto user;

    public UserDetailDto() {
    }

    public UserDetailDto(Long id, Integer age, String address, String phoneNumber, UserDto user) {
        this.id = id;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

//    //for upload
//    public UserDetailDto(Long id, Integer age, String address) {
//        this.id = id;
//        this.age = age;
//        this.address = address;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public static UserDetailDto mapEntityToDto(UserDetailEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDetailDto dto = new UserDetailDto();
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

//    //for upload
//    public static UserDetailDto mapCsvEntityToDto(UserDetailEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//        UserDetailDto dto = new UserDetailDto();
//        dto.setId(entity.getId());
//        dto.setAge(entity.getAge());
//        dto.setAddress(entity.getAddress());
//        return dto;
//    }

    public static UserDetailEntity mapDtoToEntity(UserDetailDto dto) {
        if (dto == null) {
            return null;
        }
        UserDetailEntity entity = new UserDetailEntity();
        entity.setId(dto.getId());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }
}
