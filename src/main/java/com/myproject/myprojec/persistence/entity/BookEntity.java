package com.myproject.myprojec.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "book", targetEntity = BookAuthorEntity.class)
    private List<BookAuthorEntity> bookAuthorEntityList;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @OneToMany(mappedBy = "book", targetEntity = UserRatedBookEntity.class)
    private List<UserRatedBookEntity> userRatedBookEntityList;
    @OneToMany(mappedBy = "book", targetEntity = BookGenreEntity.class)
    private List<BookGenreEntity> bookGenreEntityList;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "year_of_publication", nullable = false)
    private Integer yearOfPublication;

}
