//package com.myproject.myprojec.model;
//
//import com.myproject.myprojec.model.entity.UserDetailEntity;
//import com.myproject.myprojec.model.entity.UserEntity;
//import com.myproject.myprojec.model.entity.UsersRatedBooksEntity;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//
//public class UserWrapper {
//
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String username;
//    private String password;
//    private List<UsersRatedBooksEntity> usersRatedBooksEntityList;
//    private UserDetailEntity userDetailEntity;
//
//    public UserWrapper(Long id, String firstName, String lastName, String email, String username, String password, List<UsersRatedBooksEntity> usersRatedBooksEntityList, UserDetailEntity userDetailEntityList) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
//        this.userDetailEntity = userDetailEntity;
//    }
//
//    public UserWrapper(UserEntity userEntity) {
//        this.id = userEntity.getId();
//        this.firstName = userEntity.getFirstName();
//        this.lastName = userEntity.getLastName();
//        this.email = userEntity.getEmail();
//        this.username = userEntity.getUsername();
//        this.password = userEntity.getPassword();
//        List<UsersRatedBooksEntity> usersRatedBooksEntityList = userEntity.getUsersRatedBooksList();
//        if (!CollectionUtils.isEmpty(usersRatedBooksEntityList)) {
//            this.usersRatedBooksEntityList = userEntity.getUsersRatedBooksList();
//        }
//        UserDetailEntity userDetailEntity = userEntity.getUserDetailEntity();
//        if (userDetailEntity != null) {
//            this.userDetailEntity = userEntity.getUserDetailEntity();
//        }
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
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<UsersRatedBooksEntity> getUsersRatedBooksEntityList() {
//        return usersRatedBooksEntityList;
//    }
//
//    public void setUsersRatedBooksEntityList(List<UsersRatedBooksEntity> usersRatedBooksEntityList) {
//        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
//    }
//
//    public UserDetailEntity getUserDetailEntity() {
//        return userDetailEntity;
//    }
//
//    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
//        this.userDetailEntity = userDetailEntity;
//    }
//}
