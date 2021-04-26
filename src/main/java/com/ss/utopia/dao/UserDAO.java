package com.ss.utopia.dao;

import com.ss.utopia.entity.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO implements ResultSetExtractor<List<User>> {

    public Integer addUser(User user) throws SQLException, ClassNotFoundException {
        return jdbcTemplate.update("INSERT INTO user(role_id, given_name, family_name," +
                "username, email, password, phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                user.getUserRole().getId(),
                user.getGivenName(),
                user.getFamilyName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone());

    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException{
        jdbcTemplate.update("UPDATE user SET role_id = ?, given_name = ?, family_name = ?, username = ?, " +
                "email = ?, password = ?, phone = ? WHERE user.id = ?", user.getUserRole().getId(),
                user.getGivenName(),
                user.getFamilyName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getId());

    }

    public void deleteUser(User user) throws SQLException, ClassNotFoundException{
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", new Object[]{user.getId()});
    }

    public User getUserById(Integer id) throws SQLException, ClassNotFoundException {
        List<User> users = jdbcTemplate.query("SELECT * FROM utopia.user\n"+
                "JOIN user_role ON user_role.id = role_id WHERE user.id = ?", new Object[]{id},this);

        if(users.size() > 0)
            return users.get(0);

        return null;
    }

    public User getUserByName(String name) throws SQLException, ClassNotFoundException {
        List<User> users = jdbcTemplate.query("SELECT * FROM utopia.user\n"+
                "JOIN user_role ON user_role.id = role_id WHERE given_name = ?", new Object[]{name},this);

        if(users != null && users.size() > 0)
            return users.get(0);

        return null;
    }

    public User getUserByCred(String username, String password) throws SQLException, ClassNotFoundException {
        List<User> users = jdbcTemplate.query("SELECT * FROM utopia.user\n"+
                "JOIN user_role ON user_role.id = role_id WHERE username = ? AND password = ?", new Object[]{username, password}, this);

        if(users != null && users.size() > 0)
            return users.get(0);

        return null;
    }


    public List<User> getAllEmployees() throws SQLException, ClassNotFoundException {
        return jdbcTemplate.query("""
                SELECT * FROM utopia.user
                JOIN user_role ON user_role.id = role_id
                AND user_role.name = 'Employee'""", this);
    }

    public List<User> getAllTravelers() throws SQLException, ClassNotFoundException{
        return jdbcTemplate.query("""
                SELECT * FROM utopia.user
                JOIN user_role ON user_role.id = role_id
                AND user_role.name = 'Traveler'""", this);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
       return jdbcTemplate.query("SELECT * FROM utopia.user\n" +
                "JOIN user_role ON user_role.id = role_id", this);
    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();

        while(rs.next())
            users.add(User.toObject(rs));

        return users;
    }
}
