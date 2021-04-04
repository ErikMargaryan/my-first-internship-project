package com.myproject.myprojec.persistence.rpository;

//import com.myproject.myprojec.model.UserWrapper;
import com.myproject.myprojec.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
