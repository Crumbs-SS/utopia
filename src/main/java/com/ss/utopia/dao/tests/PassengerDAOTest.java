package com.ss.utopia.dao.tests;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Passenger;
import com.ss.utopia.service.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDAOTest {
     ConnectionUtil connUtil = new ConnectionUtil();

    @Test
    void addPassenger() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            PassengerDAO pdao = new PassengerDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.getAllBookings().get(0);
            Date date = Date.valueOf(LocalDate.now());
            Passenger passenger = new Passenger(booking, "ELIJAH", "BROOKS", date, "MALE", "LANE");
            pdao.addPassenger(passenger);

            assertEquals(passenger.getId(), pdao.getPassengerByBooking(booking).getId());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }

    @Test
    void updatePassenger() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            PassengerDAO pdao = new PassengerDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.getAllBookings().get(0);

            Passenger passenger = pdao.getPassengerByBooking(booking);
            passenger.setAddress("13134 LANE");

            pdao.updatePassenger(passenger);

            assertEquals(passenger.getAddress(), pdao.getPassengerByBooking(booking).getAddress());
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }

    @Test
    void deletePassenger() throws SQLException {
        Connection conn = null;
        try{
            conn = connUtil.getConnection();
            PassengerDAO pdao = new PassengerDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = bookingDAO.getAllBookings().get(0);;

            Passenger passenger = pdao.getPassengerByBooking(booking);

            pdao.deletePassenger(passenger);
            assertNull(pdao.getPassengerByBooking(booking));
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.close();
        }
    }
}