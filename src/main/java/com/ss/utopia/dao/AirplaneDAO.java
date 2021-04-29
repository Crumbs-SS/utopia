package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AirplaneDAO extends BaseDAO implements ResultSetExtractor<List<Airplane>> {

    public Integer addAirplane(Airplane airplane) {
        return jdbcTemplate.update("INSERT INTO airplane(id, type_id) VALUES (?, ?)", airplane.getId(),
                airplane.getAirplaneType().getId());
    }

    public int updateAirplane(Airplane airplane) {
        return jdbcTemplate.update("UPDATE airplane SET type_id = ? WHERE id = ?", airplane.getAirplaneType().getId(), airplane.getId());
    }

    public int deleteAirplane(Airplane airplane) {
        return jdbcTemplate.update("DELETE FROM airplane WHERE id = ?", airplane.getId());
    }

    public Airplane getAirplaneById(Integer id){
        List<Airplane> airplanes = jdbcTemplate.query("SELECT airplane.id, type_id, max_capacity FROM utopia.airplane\n" +
                "JOIN airplane_type ON airplane_type.id = type_id AND airplane.id = ?", new Object[]{id},this);
        if(airplanes != null && airplanes.size() > 0) {
            return airplanes.get(0);
        }
        return null;
    }

    public List<Airplane> getAllAirplanes(){
        return jdbcTemplate.query("SELECT airplane.id, type_id, max_capacity FROM utopia.airplane\n" +
                "JOIN airplane_type ON airplane_type.id = type_id;", this);
    }

    @Override
    public List<Airplane> extractData(ResultSet rs) throws SQLException {
       List<Airplane> airplanes = new ArrayList<>();

       while(rs.next())
           airplanes.add(Airplane.toObject(rs));

       return airplanes;
    }
}
