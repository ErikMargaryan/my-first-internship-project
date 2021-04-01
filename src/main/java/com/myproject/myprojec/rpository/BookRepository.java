package com.myproject.myprojec.rpository;

import com.myproject.myprojec.model.BookWrapper;
import com.myproject.myprojec.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT new com.myproject.myprojec.model.BookWrapper(b.id," +
            "b.title," +
            "b.bookAuthorEntityList," +
            "b.price," +
            "b.usersRatedBooksEntityList," +
            "b.bookGenreEntityList) FROM BookEntity b")
    Page<BookWrapper> findALLWithPagination(Pageable pageable);
}
