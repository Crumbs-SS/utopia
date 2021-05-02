package com.ss.utopia.service;


import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seats;
import com.ss.utopia.repo.FlightRepository;
import com.ss.utopia.repo.SeatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Autowired private EmployeeService employeeService;

    @MockBean private FlightRepository flightRepository;
    @MockBean private SeatRepository seatRepository;

    @Test
    public void getFlightByIdTest(){
        Mockito.when(flightRepository.findById(0)).thenReturn(Optional.of(MockUtil.getFlight()));
        assertNotEquals(null, employeeService.getFlight(0));
    }

    @Test
    public void getSeatsTest() {
        Mockito.when(seatRepository.findAll()).thenReturn(MockUtil.getMockSeats());
        assertEquals(1, employeeService.getSeats().getBody().size());
    }

    @Test
    public void updateFlightTest() {
        Flight flight = new Flight(1);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        assertEquals(null, employeeService.updateFlight(flight));
        assertEquals(flight, employeeService.updateFlight(flight).getBody());
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    public void updateSeatsTest(){
        final Seats seat = new Seats();
        Mockito.when(seatRepository.save(seat)).thenReturn(seat);
        assertNotEquals(null, employeeService.updateSeats(seat));
    }

}
