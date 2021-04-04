package com.myproject.myprojec.persistence.rpository;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatedBookRepository extends JpaRepository<UserRatedBookEntity, Long> {
}
