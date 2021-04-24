package com.ss.utopia.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private Integer id;
    private UserRole userRole;

    private String givenName;
    private String familyName;
    private String username;
    private String email;
    private String password;
    private String phone;

    public User(String givenName, String familyName, String username, String email, String password, String phone) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(Integer agentId) {
        this.id = agentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static User toObject(ResultSet rs) throws SQLException {
        String givenName = rs.getString("given_name");
        String familyName = rs.getString("family_name");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phone = rs.getString("phone");
        String roleName = rs.getString("name");
        Integer userRoleId = rs.getInt("role_id");
        Integer id = rs.getInt("user.id");

        User user = new User(givenName, familyName,
                username, email, password, phone);
        user.setId(id);

        user.setUserRole(new UserRole(userRoleId, roleName));
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userRole=" + userRole.getName() +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
