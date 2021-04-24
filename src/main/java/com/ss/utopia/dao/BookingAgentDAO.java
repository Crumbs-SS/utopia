package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingAgent;
import com.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingAgentDAO extends BaseDAO<BookingAgent>{
    public BookingAgentDAO(Connection conn) {
        super(conn);
    }

    public void addBookingAgent(BookingAgent bookingAgent) throws SQLException, ClassNotFoundException{
        save("INSERT INTO booking_agent(booking_id, agent_id) VALUES (?, ?)", new Object[]{
                bookingAgent.getBooking().getId(),
                bookingAgent.getAgent().getId()
        });
    }

    public void deleteBookingAgent(BookingAgent bookingAgent) throws SQLException, ClassNotFoundException{
        save("DELETE FROM booking_agent WHERE booking_id = ?", new Object[]{
                bookingAgent.getBooking().getId()
        });
    }

    public BookingAgent getBookingAgentFromAgent(User user) throws SQLException, ClassNotFoundException{
        List<BookingAgent> bookingAgents = read("SELECT * FROM booking_agent WHERE agent_id = ?", new Object[]{
                user.getId()
        });

        if(!bookingAgents.isEmpty())
            return bookingAgents.get(0);
        return null;
    }

    public BookingAgent getBookingAgentFromBooking(Booking booking) throws SQLException, ClassNotFoundException{
        List<BookingAgent> bookingAgents = read("SELECT * FROM booking_agent WHERE booking_id = ?", new Object[]{
                booking.getId()
        });

        if(!bookingAgents.isEmpty())
            return bookingAgents.get(0);
        return null;
    }

    public List<BookingAgent> getAllBookingAgents() throws SQLException, ClassNotFoundException{
        return read("SELECT * FROM booking_agent", null);
    }

    @Override
    public List<BookingAgent> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookingAgent> bookingAgents = new ArrayList<>();

        while(rs.next())
            bookingAgents.add(BookingAgent.toObject(rs));

        return bookingAgents;
    }
}
