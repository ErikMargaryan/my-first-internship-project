package com.myproject.myprojec.rpository;

import com.myproject.myprojec.model.entity.UsersRatedBooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRatedBooksRepository extends JpaRepository<UsersRatedBooksEntity, Long> {
}
