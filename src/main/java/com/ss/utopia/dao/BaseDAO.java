package com.ss.utopia.dao;

import java.sql.*;
import java.util.List;

abstract class BaseDAO<T> {
    public static Connection conn;

    public BaseDAO(Connection conn){
        this.conn = conn;
    }

    public void save(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int count = 1;
        for(Object o: vals){
            pstmt.setObject(count, o);
            count++;
        }

        pstmt.executeUpdate();
    }

    public Integer saveWithPK(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int count = 1;
        for(Object o: vals){
            pstmt.setObject(count, o);
            count++;
        }

        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);

        return null;
    }


    public List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int count = 1;
        if(vals != null)
            for(Object o: vals){
                pstmt.setObject(count, o);
                count++;
            }

        ResultSet rs = pstmt.executeQuery();
        return extractData(rs);
    }

     public abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;

}
