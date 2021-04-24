package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO extends BaseDAO<Passenger> {

    public PassengerDAO(Connection conn) {
        super(conn);
    }

    public Integer addPassenger(Passenger passenger) throws SQLException, ClassNotFoundException {
        Integer key = saveWithPK("INSERT INTO passenger(booking_id, given_name, family_name, dob, gender, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)", new Object[]{
                        passenger.getBooking().getId(),
                passenger.getGiven_name(),
                passenger.getFamily_name(),
                passenger.getDate(),
                passenger.getGender(),
                passenger.getAddress()
        });

        passenger.setId(key);

        return key;
    }

    public void updatePassenger(Passenger passenger) throws SQLException, ClassNotFoundException{
        save("UPDATE passenger SET booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?, address = ? " +
                        "WHERE id = ?",
                new Object[]{
                        passenger.getBooking().getId(),
                        passenger.getGiven_name(),
                        passenger.getFamily_name(),
                        passenger.getDate(),
                        passenger.getGender(),
                        passenger.getAddress(),
                        passenger.getId()
                });
    }

    public void deletePassenger(Passenger passenger) throws SQLException, ClassNotFoundException{
        save("DELETE FROM passenger WHERE id = ?", new Object[]{passenger.getId()});
    }

    public Passenger getPassengerByBooking(Booking booking) throws  SQLException, ClassNotFoundException{
        List<Passenger> passengers = read("SELECT * FROM passenger WHERE booking_id = ?", new Object[]{
                booking.getId()
        });

        if(!passengers.isEmpty())
            return passengers.get(0);
        return null;
    }


    @Override
    public List<Passenger> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Passenger> passengers = new ArrayList<>();

        while(rs.next())
            passengers.add(Passenger.toObject(rs));

        return passengers;
    }
}
