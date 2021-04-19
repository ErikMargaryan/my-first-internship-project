package com.myproject.myprojec.csvUpload.csvModel;

public class UserDetail {

//    @CsvBindByName(column = "User-ID")
//    private Long id;
//    @CsvBindAndSplitByName(column = "Location", elementType = String.class, splitOn = ",")
//    private List<String> address;
//    @CsvBindByName(column = "Age")
//    private Integer Age;
//
//    public UserDetail() {
//    }
//
//    public UserDetail(Long id, Integer age, List<String> address) {
//        this.id = id;
//        Age = age;
//        this.address = address;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getAge() {
//        return Age;
//    }
//
//    public void setAge(Integer age) {
//        Age = age;
//    }
//
//    public List<String> getAddress() {
//        return address;
//    }
//
//    public void setAddress(List<String> address) {
//        this.address = address;
//    }
//
//    public static UserDetailEntity mapCsvToEntity(UserDetail csv) {
//        UserDetailEntity entity = new UserDetailEntity();
//        entity.setId(csv.getId());
//        entity.setAge(csv.getAge());
////        List<String> address = csv.getAddress();
////        entity.setAddress();
//        return entity;
//    }
//
//    public static List<UserDetailEntity> mapCsvToEntityList(Collection<UserDetail> userDetailCollection) {
//        return userDetailCollection.stream()
//                .map(UserDetail::mapCsvToEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String toString() {
//        return "UserDetail{" +
//                "id=" + id +
//                ", Age=" + Age +
//                ", address='" + address + '\'' +
//                '}';
//    }
}
