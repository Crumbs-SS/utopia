package com.ss.utopia.dao;

import com.ss.utopia.entity.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRoleDAO extends BaseDAO<UserRole> {

    public UserRoleDAO(Connection conn) {
        super(conn);
    }

    public List<UserRole> getAllUserRoles() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM user_role", null);
    }

    @Override
    public List<UserRole> extractData(ResultSet rs) throws SQLException {
        List<UserRole> userRoles = new ArrayList<>();

        while(rs.next())
            userRoles.add(UserRole.toObject(rs));

        return userRoles;
    }
}
