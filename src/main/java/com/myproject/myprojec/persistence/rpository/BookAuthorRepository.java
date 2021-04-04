package com.myproject.myprojec.persistence.rpository;

import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Long> {
}
