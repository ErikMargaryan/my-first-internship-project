package com.myproject.myprojec.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BookAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @NonNull
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @NonNull
    private AuthorEntity author;

}
