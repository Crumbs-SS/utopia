package com.ss.utopia.dao;

import com.ss.utopia.entity.AirplaneType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType>{

    public AirplaneTypeDAO(Connection conn) {
        super(conn);
    }

    public Integer addAirplaneType(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        Integer key = saveWithPK("INSERT INTO airplane_type(id, max_capacity) VALUES (?, ?)", new Object[]{
           airplaneType.getId(),
           airplaneType.getMaxCapacity()
        });
        airplaneType.setId(key);
        return key;
    }

    public void updateAirplaneType(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        save("UPDATE airplane_type SET max_capacity = ? WHERE id = ?", new Object[]{
                airplaneType.getMaxCapacity(),
                airplaneType.getId()
        });
    }

    public void deleteAirplaneType(AirplaneType airplaneType) throws SQLException, ClassNotFoundException{
        save("DELETE FROM airplane_type WHERE id = ?", new Object[]{airplaneType.getId()});
    }

    public AirplaneType getAirplaneTypeByID(Integer id) throws SQLException, ClassNotFoundException {
       List<AirplaneType> airplaneTypes =  read("SELECT * FROM airplane_type WHERE id = ?", new Object[]{id});

       if(airplaneTypes.size() > 0)
           return airplaneTypes.get(0);
       return null;
    }

    public List<AirplaneType> getAllAirplaneTypes() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM airplane_type", null);
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
