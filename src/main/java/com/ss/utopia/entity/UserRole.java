package com.ss.utopia.entity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRole {
    private Integer id;
    private String name;

    public UserRole(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserRole toObject(ResultSet rs) throws SQLException {
        Integer userRoleId = rs.getInt("id");
        String userRoleName = rs.getString("name");
        return new UserRole(userRoleId, userRoleName);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
