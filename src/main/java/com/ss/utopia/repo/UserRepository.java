package com.ss.utopia.repo;

import com.ss.utopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM user u WHERE u.username = ?1 and u.password = ?2")
    User authenticateUser(String username, String password);

    @Query("SELECT * from user where role_id = 2")
    List<User> getTravellers();

    @Query("SELECT * from user where role_id = 3")
    List<User> getEmployees();

    @Modifying
    @Query("DELETE FROM user where id = ?1 and role_id = 2")
    void deleteTraveler(Integer id);

    @Modifying
    @Query("DELETE FROM user where id = ?1 and role_id = 3")
    void deleteEmployee(Integer id);



}
