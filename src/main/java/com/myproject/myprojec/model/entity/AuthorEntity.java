package com.myproject.myprojec.model.entity;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "authors")
    private List<BookAuthorEntity> bookAuthorEntityList;

    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String firstName, String lastName, List<BookAuthorEntity> bookAuthorEntityList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookAuthorEntityList = bookAuthorEntityList;
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

    public List<BookAuthorEntity> getBookAuthorEntityList() {
        return bookAuthorEntityList;
    }

    public void setBookAuthorEntityList(List<BookAuthorEntity> bookAuthorEntityList) {
        this.bookAuthorEntityList = bookAuthorEntityList;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
