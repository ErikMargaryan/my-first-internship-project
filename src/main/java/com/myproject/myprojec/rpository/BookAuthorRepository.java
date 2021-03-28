package com.myproject.myprojec.rpository;

import com.myproject.myprojec.model.entity.BookAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Long> {
}
