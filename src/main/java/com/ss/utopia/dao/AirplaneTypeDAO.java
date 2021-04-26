package com.ss.utopia.dao;

import com.ss.utopia.entity.AirplaneType;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDAO extends BaseDAO implements ResultSetExtractor<List<AirplaneType>> {

    public Integer addAirplaneType(AirplaneType airplaneType) {
        return jdbcTemplate.update("INSERT INTO airplane_type(id, max_capacity) VALUES (?, ?)", airplaneType.getId(),
                airplaneType.getMaxCapacity());
    }

    public void updateAirplaneType(AirplaneType airplaneType) {
        jdbcTemplate.update("UPDATE airplane_type SET max_capacity = ? WHERE id = ?", airplaneType.getMaxCapacity(),
                airplaneType.getId());
    }

    public void deleteAirplaneType(AirplaneType airplaneType) {
        jdbcTemplate.update("DELETE FROM airplane_type WHERE id = ?", airplaneType.getId());
    }

    public AirplaneType getAirplaneTypeByID(Integer id) {
       List<AirplaneType> airplaneTypes =  jdbcTemplate.query("SELECT * FROM airplane_type WHERE id = ?", new Object[]{id},this);

       if(airplaneTypes != null && airplaneTypes.size() > 0)
           return airplaneTypes.get(0);
       return null;
    }

    public List<AirplaneType> getAllAirplaneTypes() {
        return jdbcTemplate.query("SELECT * FROM airplane_type", this);
    }

    @Override
    public List<AirplaneType> extractData(ResultSet rs) throws SQLException {
        List<AirplaneType> airplaneTypes = new ArrayList<>();
        while(rs.next()){
            airplaneTypes.add(AirplaneType.toObject(rs));
        }
        return airplaneTypes;
    }
}
