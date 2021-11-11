package com.myproject.myprojec.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
