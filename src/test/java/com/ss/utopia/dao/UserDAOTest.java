package com.ss.utopia.dao;

import com.ss.utopia.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {

    @Autowired
    UserRoleDAO urdao;

    @Autowired
    UserDAO udao;

    @Test
    @Order(1)
    void addUser() throws SQLException, ClassNotFoundException {
        User user = new User("Test", "Guy", "testguy", "testguy@gmail.com",
                "password1", "1010101010");
        user.setUserRole(urdao.getAllUserRoles().get(0));
        Integer key = udao.addUser(user);
        user.setId(key);

//        assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
    }

    @Test
    @Order(2)
    void updateUser() throws SQLException, ClassNotFoundException {
        User user = udao.getUserByName("Test");
        user.setPassword("password5");
        udao.updateUser(user);

        assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
    }

    @Test
    @Order(3)
    void deleteUser() throws SQLException, ClassNotFoundException {
        User user = udao.getUserByName("Test");
        udao.deleteUser(user);

        assertNull(udao.getUserById(user.getId()));
    }

    @Test
    @Order(4)
    void getAllEmployees() throws SQLException, ClassNotFoundException {
        List<User> users = udao.getAllEmployees();

        String[] expectedRoles = new String[users.size()];
        Arrays.fill(expectedRoles, "Employee");

        String[] roles = users.stream()
                .map(user -> user.getUserRole().getName())
                .collect(Collectors.toList())
                .toArray(new String[users.size()]);

        assertArrayEquals(expectedRoles, roles);
    }

    @Test
    @Order(5)
    void getAllTravelers() throws SQLException, ClassNotFoundException {
        List<User> users = udao.getAllTravelers();

        String[] expectedRoles = new String[users.size()];
        Arrays.fill(expectedRoles, "Traveler");

        String[] roles = users.stream()
                .map(user -> user.getUserRole().getName())
                .collect(Collectors.toList())
                .toArray(new String[users.size()]);

        assertArrayEquals(expectedRoles, roles);
    }
}