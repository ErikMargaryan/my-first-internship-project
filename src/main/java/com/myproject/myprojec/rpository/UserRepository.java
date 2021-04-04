package com.myproject.myprojec.rpository;

//import com.myproject.myprojec.model.UserWrapper;
import com.myproject.myprojec.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    @Query("SELECT new com.myproject.myprojec.model.UserWrapper(u.id," +
//            "u.firstName," +
//            "u.lastName," +
//            "u.email," +
//            "u.username," +
//            "u.password," +
//            "u.usersRatedBooksEntityList," +
//            "u.userDetailEntity) FROM UserEntity u")
//    Page<UserWrapper> findALLWithPagination(Pageable pageable);
}
