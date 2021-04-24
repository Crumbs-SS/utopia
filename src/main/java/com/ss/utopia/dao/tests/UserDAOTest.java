package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import com.ss.utopia.entity.User;
import com.ss.utopia.entity.UserRole;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addUser() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            UserRoleDAO urdao = new UserRoleDAO(conn);
            UserDAO udao = new UserDAO(conn);

            User user = new User("Test", "Guy", "testguy", "testguy@gmail.com",
                    "password1", "1010101010");
            user.setUserRole(urdao.getAllUserRoles().get(0));
            udao.addUser(user);

            assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void updateUser() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            UserDAO udao = new UserDAO(conn);

            User user = udao.getUserByName("Test");
            user.setPassword("password5");
            udao.updateUser(user);

            assertEquals(user.toString(), udao.getUserById(user.getId()).toString());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void deleteUser() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            UserDAO udao = new UserDAO(conn);

            User user = udao.getUserByName("Test");
            udao.deleteUser(user);
            assertNull(udao.getUserById(user.getId()));

            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void getAllEmployees() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            UserDAO udao = new UserDAO(conn);
            List<User> users = udao.getAllEmployees();

            String[] expectedRoles = new String[users.size()];
            for (int i = 0; i < expectedRoles.length; i++)
                expectedRoles[i] = "Employee";


            String[] roles = users.stream()
                    .map(user -> user.getUserRole().getName())
                    .collect(Collectors.toList())
                    .toArray(new String[users.size()]);

            assertArrayEquals(expectedRoles, roles);
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

    @Test
    void getAllTravelers() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            UserDAO udao = new UserDAO(conn);
            List<User> users = udao.getAllTravelers();

            String[] expectedRoles = new String[users.size()];
            for (int i = 0; i < expectedRoles.length; i++)
                expectedRoles[i] = "Traveler";

            String[] roles = users.stream()
                    .map(user -> user.getUserRole().getName())
                    .collect(Collectors.toList())
                    .toArray(new String[users.size()]);

            assertArrayEquals(expectedRoles, roles);
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.close();
        }
    }

}