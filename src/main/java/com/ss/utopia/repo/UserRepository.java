package com.ss.utopia.repo;

import com.ss.utopia.entity.User;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM user u WHERE u.username = ?1 AND u.password = ?2")
    User authenticateUser(String username, String password);

    @Query("SELECT u FROM user u where u.userRole.id = 2")
    List<User> getTravellers();

    @Query("SELECT u FROM user u WHERE u.username = ?1")
    User findUserByUsername(String username);

    @Query("SELECT u FROM user u where u.userRole.id = 3")
    List<User> getEmployees();


    // TODO: Possibly consider just doing user.delete(User user)
    @Modifying
    @Query("DELETE FROM user u where u.id = ?1 AND u.userRole.id = 2")
    void deleteTraveler(Integer id);

    // TODO: Possibly consider just doing user.delete(User user)
    @Modifying
    @Query("DELETE FROM user u where u.id = ?1 AND u.userRole.id = 3")
    void deleteEmployee(Integer id);



}
