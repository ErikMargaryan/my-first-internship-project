package com.myproject.myprojec.persistence.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "author", targetEntity = BookAuthorEntity.class)
    private List<BookAuthorEntity> bookAuthorEntityList;



}
