package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(Long id, UserEntity user, RoleEntity role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity roles) {
        this.role = roles;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, role);
    }
}

