package com.myproject.myprojec.persistence.rpository;

//import com.myproject.myprojec.model.BookWrapper;
import com.myproject.myprojec.persistence.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT b FROM BookEntity b")
//    @Query("SELECT '*' FROM BookEntity")
    Page<BookEntity> findALLWithPagination(Pageable pageable);
}
