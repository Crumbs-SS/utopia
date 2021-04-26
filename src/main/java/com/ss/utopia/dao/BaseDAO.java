package com.ss.utopia.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
abstract class BaseDAO{
    @Autowired
   JdbcTemplate jdbcTemplate;


}
