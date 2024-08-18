package com.example.BlogPost.Repository;

import com.example.BlogPost.Model.User;
import com.example.BlogPost.Model.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //created for post in usercontroller
    @Query(nativeQuery = true,value ="SELECT username,user_id,password FROM user WHERE username = :username" )
    Optional<User> findUserByUserName(@Param("username") String username);


    @Query(nativeQuery = true,value = "SELECT user_id FROM user where username=:username")
    Long findUserIdByUserName(@Param("username") String username);


}

