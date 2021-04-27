package com.ss.utopia.dao;

import com.ss.utopia.entity.UserRole;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRoleDAO extends BaseDAO implements ResultSetExtractor<List<UserRole>> {

    public List<UserRole> getAllUserRoles() throws SQLException, ClassNotFoundException {
        return jdbcTemplate.query("SELECT * FROM user_role", this);
    }

    @Override
    public List<UserRole> extractData(ResultSet rs) throws SQLException {
        List<UserRole> userRoles = new ArrayList<>();

        while(rs.next())
            userRoles.add(UserRole.toObject(rs));

        return userRoles;
    }
}
