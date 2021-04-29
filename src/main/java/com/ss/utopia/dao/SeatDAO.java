package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Seat;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void deleteSeat(Seat seat){
        jdbcTemplate.update("DELETE FROM seat WHERE id = ?", seat.getFlight().getId());
    }

    @Override
    public List<Seat> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Seat> seats = new ArrayList<>();

        while(rs.next())
            seats.add(Seat.toObject(rs));

        return seats;

    }
}
