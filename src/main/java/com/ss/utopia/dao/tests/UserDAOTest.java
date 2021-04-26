package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    @Test
    void addUser() throws SQLException {
        try{
            UserRoleDAO urdao = new UserRoleDAO();
            UserDAO udao = new UserDAO();

            User user = new User("Test", "Guy", "testguy", "testguy@gmail.com",
                    "password1", "1010101010");
            user.setUserRole(urdao.getAllUserRoles().get(0));
            udao.addUser(user);

            assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateUser() throws SQLException {
        try{
            UserDAO udao = new UserDAO();

            User user = udao.getUserByName("Test");
            user.setPassword("password5");
            udao.updateUser(user);

            assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteUser() throws SQLException {
        try{
            UserDAO udao = new UserDAO();

            User user = udao.getUserByName("Test");
            udao.deleteUser(user);
            assertNull(udao.getUserById(user.getId()));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void getAllEmployees() throws SQLException {
        try{
            UserDAO udao = new UserDAO();
            List<User> users = udao.getAllEmployees();

            String[] expectedRoles = new String[users.size()];
            for (int i = 0; i < expectedRoles.length; i++)
                expectedRoles[i] = "Employee";


            String[] roles = users.stream()
                    .map(user -> user.getUserRole().getName())
                    .collect(Collectors.toList())
                    .toArray(new String[users.size()]);

            assertArrayEquals(expectedRoles, roles);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void getAllTravelers() throws SQLException {
        try{
            UserDAO udao = new UserDAO();
            List<User> users = udao.getAllTravelers();

            String[] expectedRoles = new String[users.size()];
            for (int i = 0; i < expectedRoles.length; i++)
                expectedRoles[i] = "Traveler";

            String[] roles = users.stream()
                    .map(user -> user.getUserRole().getName())
                    .collect(Collectors.toList())
                    .toArray(new String[users.size()]);

            assertArrayEquals(expectedRoles, roles);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}