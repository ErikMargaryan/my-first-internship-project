package com.myproject.myprojec.persistence.repository;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatedBookRepository extends JpaRepository<UserRatedBookEntity, Long> {
    @Query("select ur from UserRatedBookEntity ur")
    Page<UserRatedBookEntity> findALLWithPagination(Pageable pageable);
}
