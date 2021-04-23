package com.myproject.myprojec.service.dto;

import com.myproject.myprojec.persistence.entity.RoleEntity;
import com.myproject.myprojec.persistence.entity.UserEntity;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.persistence.entity.UserRoleEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private List<UserRatedBookDto> userRatedBookDtoList;
    private String role;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, Integer age, String address, String phoneNumber, String email, String username, String password, List<UserRatedBookDto> userRatedBookDtoList, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRatedBookDtoList = userRatedBookDtoList;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        List<UserRatedBookEntity> userRatedBookEntityList = entity.getUsersRatedBooksList();
        if (!CollectionUtils.isEmpty(userRatedBookEntityList)) {
            dto.setUsersRatedBooksDtoList(userRatedBookEntityList.stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList()));
        }
        dto.setRole(getRoles(entity));
        return dto;
    }

    public static UserEntity mapDtoToEntity(UserDto dto, UserEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        List<UserRatedBookDto> userRatedBookDtoList = dto.getUsersRatedBooksDtoList();
        if (!CollectionUtils.isEmpty(userRatedBookDtoList)) {
            entity.setUsersRatedBooksList(userRatedBookDtoList.stream().map(UserRatedBookDto::mapDtoToEntity).collect(Collectors.toList()));
        }
        return entity;
    }

    private static String getRoles(UserEntity entity) {

        return entity.getListOfUserRole().stream()
                .map(UserRoleEntity::getRole)
                .map(RoleEntity::getName)
                .collect(Collectors.joining(", "));
    }
}
