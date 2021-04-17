package com.myproject.myprojec.service.model.csv;

import com.opencsv.bean.CsvBindByName;

public class UserDetail {
    @CsvBindByName(column = "User-ID")
    private Long id;
    @CsvBindByName(column = "Age")
    private Integer Age;
    @CsvBindByName(column = "Location")
    private String address;

    public UserDetail() {
    }

    public UserDetail(Long id, Integer age, String address) {
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

//    public static UserDetailEntity mapCsvToEntity(UserDetail csv) {
//        UserDetailEntity entity = new UserDetailEntity();
//        entity.setId(csv.getId());
//        entity.setAge(csv.getAge());
//        entity.setAddress(csv.getAddress());
//        return entity;
//    }

//    public static UserDetailDto mapCsvToDto(UserDetail csv) {
//        UserDetailDto dto = new UserDetailDto();
//        dto.setId(csv.getId());
//        dto.setAge(csv.getAge());
//        dto.setAddress(csv.getAddress());
//        return dto;
//    }

//    public static List<UserDetailEntity> mapCsvToEntityList(Collection<UserDetail> userDetailCollection) {
//        return userDetailCollection.stream()
//                .map(UserDetail::mapCsvToEntity)
//                .collect(Collectors.toList());
//    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", Age=" + Age +
                ", address='" + address + '\'' +
                '}';
    }
}
