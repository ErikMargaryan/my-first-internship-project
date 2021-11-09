package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 10485760)
    private String password;

    @OneToMany(mappedBy = "user", targetEntity = UserRatedBookEntity.class)
    private List<UserRatedBookEntity> userRatedBookEntityList;

    @OneToMany(mappedBy = "user", targetEntity = UserRoleEntity.class)
    private List<UserRoleEntity> listOfUserRole;

    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, Integer age, String address, String phoneNumber, String email, String username, String password, List<UserRatedBookEntity> userRatedBookEntityList, List<UserRoleEntity> listOfUserRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRatedBookEntityList = userRatedBookEntityList;
        this.listOfUserRole = listOfUserRole;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public List<UserRatedBookEntity> getUsersRatedBooksList() {
        return userRatedBookEntityList;
    }

    public void setUsersRatedBooksList(List<UserRatedBookEntity> userRatedBookEntityList) {
        this.userRatedBookEntityList = userRatedBookEntityList;
    }

    public List<UserRoleEntity> getListOfUserRole() {
        return listOfUserRole;
    }

    public void setListOfUserRole(List<UserRoleEntity> listOfUserRole) {
        this.listOfUserRole = listOfUserRole;
    }
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + password + '\'' +
                '}';
    }
}
