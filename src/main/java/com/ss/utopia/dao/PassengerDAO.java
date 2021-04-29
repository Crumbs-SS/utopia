package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class PassengerDAO extends BaseDAO implements ResultSetExtractor<List<Passenger>> {

    public Integer addPassenger(Passenger passenger) throws SQLException, ClassNotFoundException {
        return jdbcTemplate.update("INSERT INTO passenger(booking_id, given_name, family_name, dob, gender, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)", passenger.getBooking().getId(),
                passenger.getGiven_name(),
                passenger.getFamily_name(),
                passenger.getDate(),
                passenger.getGender(),
                passenger.getAddress());

    }

    public int updatePassenger(Passenger passenger) throws SQLException, ClassNotFoundException{
        return jdbcTemplate.update("UPDATE passenger SET booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?, address = ? " +
                        "WHERE id = ?",
                passenger.getBooking().getId(),
                passenger.getGiven_name(),
                passenger.getFamily_name(),
                passenger.getDate(),
                passenger.getGender(),
                passenger.getAddress(),
                passenger.getId());
    }

    public int deletePassenger(Passenger passenger) throws SQLException, ClassNotFoundException{
        return jdbcTemplate.update("DELETE FROM passenger WHERE id = ?", passenger.getId());
    }

    public List<Passenger> getPassengersByBooking(Booking booking) throws  SQLException, ClassNotFoundException{
        return jdbcTemplate.query("SELECT * FROM passenger WHERE booking_id = ?", new Object[]{
                booking.getId()}, this);
    }

    public Passenger getPassenger(Integer id) throws  SQLException, ClassNotFoundException{
        List<Passenger> passengers = jdbcTemplate.query("SELECT * FROM passenger WHERE id = ?", new Object[]{id},
                this);

        if(passengers != null && passengers.size() >0)
            return passengers.get(0);
        return null;
    }

    public List<Passenger> getAllPassengers() {
        return jdbcTemplate.query("SELECT * FROM passenger", this);
    }

    @Override
    public List<Passenger> extractData(ResultSet rs) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();

        while(rs.next())
            passengers.add(Passenger.toObject(rs));

        return passengers;
    }
}
