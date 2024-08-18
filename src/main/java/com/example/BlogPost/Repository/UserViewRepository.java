package com.example.BlogPost.Repository;

import com.example.BlogPost.Model.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserViewRepository extends JpaRepository<UserView,Long> {
    @Query(nativeQuery = true,value = "SELECT user_id,username FROM user WHERE user.user_id = :user_id")
    Optional<UserView> findById1(@Param("user_id") Long id);


    @Query(nativeQuery = true,value = "SELECT user_id,username from user order by user_id")
    List<UserView> findAll1();
}
