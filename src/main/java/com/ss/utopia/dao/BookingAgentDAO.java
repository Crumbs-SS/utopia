package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingAgent;
import com.ss.utopia.entity.User;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingAgentDAO extends BaseDAO implements ResultSetExtractor<List<BookingAgent>> {

    public void addBookingAgent(BookingAgent bookingAgent) {
        jdbcTemplate.update("INSERT INTO booking_agent(booking_id, agent_id) VALUES (?, ?)", bookingAgent.getBooking().getId(),
                bookingAgent.getAgent().getId());
    }

    public void deleteBookingAgent(BookingAgent bookingAgent) {
        jdbcTemplate.update("DELETE FROM booking_agent WHERE booking_id = ? AND agent_id = ?",
                bookingAgent.getBooking().getId(),
                bookingAgent.getAgent().getId()
        );
    }

    public BookingAgent getBookingAgentFromAgent(User user){
        List<BookingAgent> bookingAgents = jdbcTemplate.query("SELECT * FROM booking_agent WHERE agent_id = ?", new Object[]{
                user.getId()},this);

        if(bookingAgents != null && bookingAgents.size() > 0)
            return bookingAgents.get(0);
        return null;
    }

    public BookingAgent getBookingAgentFromBooking(Booking booking) {
        List<BookingAgent> bookingAgents = jdbcTemplate.query("SELECT * FROM booking_agent WHERE booking_id = ?", new Object[]{
                booking.getId()}, this::extractData);

        if(bookingAgents != null && bookingAgents.size() > 0)
            return bookingAgents.get(0);
        return null;
    }

    public List<BookingAgent> getAllBookingAgents() {
        return jdbcTemplate.query("SELECT * FROM booking_agent", this);
    }

    @Override
    public List<BookingAgent> extractData(ResultSet rs) throws SQLException {
        List<BookingAgent> bookingAgents = new ArrayList<>();

        while(rs.next())
            bookingAgents.add(BookingAgent.toObject(rs));

        return bookingAgents;
    }
}
