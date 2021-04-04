package com.myproject.myprojec.rpository;

import com.myproject.myprojec.model.entity.UserRatedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatedBookRepository extends JpaRepository<UserRatedBookEntity, Long> {
}
