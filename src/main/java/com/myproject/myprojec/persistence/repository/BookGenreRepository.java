package com.myproject.myprojec.persistence.repository;

import com.myproject.myprojec.persistence.entity.BookGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenreEntity, Long> {
}
