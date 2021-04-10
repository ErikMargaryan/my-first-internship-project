package com.myproject.myprojec.persistence.rpository;

//import com.myproject.myprojec.model.UserWrapper;

import com.myproject.myprojec.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u")
    Page<UserEntity> findALLWithPagination(Pageable pageable);
}
