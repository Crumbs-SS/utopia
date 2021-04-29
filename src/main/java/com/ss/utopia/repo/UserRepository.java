package com.ss.utopia.repo;

import com.ss.utopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM user u WHERE u.username = ?1 and u.password = ?2")
    User authenticateUser(String username, String password);
}
