package com.myproject.myprojec.rpository;

import com.myproject.myprojec.model.entity.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<GenresEntity, Long> {
}
