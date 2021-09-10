package com.myproject.myprojec.persistence.repository;

import com.myproject.myprojec.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
