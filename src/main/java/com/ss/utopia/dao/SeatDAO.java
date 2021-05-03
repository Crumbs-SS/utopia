
package com.ss.utopia.dao;

import com.ss.utopia.entity.Seat;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatDAO extends BaseDAO implements ResultSetExtractor<List<Seat>>{

    public List<Seat> getAllSeats() {
        return jdbcTemplate.query("SELECT * FROM seat", this);
    }

    public Integer addSeat(Seat seat){
        return jdbcTemplate.update("INSERT INTO seat VALUES (?, ?, ?, ?) ", seat.getFlight().getId(),
                seat.getFirst(), seat.getBusiness(), seat.getEconomy());
    }
    public void updateSeat(Seat seat){
       jdbcTemplate.update("UPDATE seat SET first = ?, business = ?, economy = ? WHERE flight_id = ?", seat.getFirst(),
                seat.getBusiness(), seat.getEconomy(), seat.getFlight().getId());
    }
    public void deleteSeat(Integer id){
        jdbcTemplate.update("DELETE FROM seat WHERE flight_id = ?", id);
    }
    public Seat getSeatFromId(Integer id) {
        List<Seat> seats = jdbcTemplate.query("SELECT * FROM seat WHERE flight_id = ?", new Object[]{id},this);

        if(seats != null && seats.size() > 0)
            return seats.get(0);
        return null;
    }

    @Override
    public List<Seat> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Seat> seats = new ArrayList<>();

        while(rs.next())
            seats.add(Seat.toObject(rs));

        return seats;

    }
}
