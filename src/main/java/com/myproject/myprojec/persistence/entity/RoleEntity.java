package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", targetEntity = UserRoleEntity.class)
    private List<UserRoleEntity> listOfUserRole;

    public RoleEntity() {
    }

    public RoleEntity(Long id, String name, List<UserRoleEntity> listOfUserRole) {
        this.id = id;
        this.name = name;
        this.listOfUserRole = listOfUserRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoleEntity> getListOfUserRole() {
        return listOfUserRole;
    }

    public void setListOfUserRole(List<UserRoleEntity> listOfUserRole) {
        this.listOfUserRole = listOfUserRole;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listOfUserRole=" + listOfUserRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
