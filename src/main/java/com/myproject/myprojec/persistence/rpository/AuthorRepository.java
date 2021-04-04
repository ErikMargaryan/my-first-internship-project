package com.myproject.myprojec.persistence.rpository;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
