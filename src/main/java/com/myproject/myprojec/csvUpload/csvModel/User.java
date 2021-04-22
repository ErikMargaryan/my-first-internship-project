package com.myproject.myprojec.csvUpload.csvModel;

import com.myproject.myprojec.persistence.entity.UserEntity;
import com.opencsv.bean.CsvBindByName;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    @CsvBindByName(column = "User-ID")
    private Long id;
    @CsvBindByName(column = "Location")
    private String address;
    @CsvBindByName(column = "Age")
    private Integer Age;

    public User() {
    }

    public User(Long id, Integer age, String address) {
        this.id = id;
        Age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static UserEntity mapCsvToEntity(User csv) {
        UserEntity entity = new UserEntity();
//        entity.setId(csv.getId());
        entity.setAge(csv.getAge());
        entity.setAddress(csv.getAddress());
        return entity;
    }

    public static List<UserEntity> mapCsvToEntityList(Collection<User> userDetailCollection) {
        return userDetailCollection.stream()
                .map(User::mapCsvToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", Age=" + Age +
                ", address='" + address + '\'' +
                '}';
    }
}
