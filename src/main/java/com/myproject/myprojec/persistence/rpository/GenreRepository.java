package com.myproject.myprojec.persistence.rpository;

import com.myproject.myprojec.persistence.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    @Query("select g from GenreEntity g")
    Page<GenreEntity> findALLWithPagination(Pageable pageable);
}
