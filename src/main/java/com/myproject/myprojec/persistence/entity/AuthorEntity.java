package com.myproject.myprojec.persistence.entity;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "authors")
    private List<BookAuthorEntity> bookAuthorEntityList;

    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String name, List<BookAuthorEntity> bookAuthorEntityList) {
        this.id = id;
        this.name = name;
        this.bookAuthorEntityList = bookAuthorEntityList;
    }
    public AuthorEntity(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                '}';
    }
}
