package com.myproject.myprojec.persistence.repository;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Query("select a from AuthorEntity a")
    Page<AuthorEntity> findALLWithPagination(Pageable pageable);
}
