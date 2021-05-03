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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Autowired private EmployeeService employeeService;

    @MockBean private FlightRepository flightRepository;
    @MockBean private SeatRepository seatRepository;

  @Test
  void getFlightByIDTest() {
      Mockito.when(flightRepository.findById(0))
              .thenReturn(Optional.of(MockUtil.getFlight()));

      assertNotEquals(null, employeeService.getFlight(0));
  }
  @Test
  void getSeats() {
      Mockito.when(seatRepository.findAll())
              .thenReturn(MockUtil.getMockSeats());

      assertEquals(MockUtil.getMockSeats().size(), employeeService.getSeats().size());
  }

  @Test
  void updateFlight() {
      Optional<Flight> flightOptional = MockUtil.getFlightOptional();
      Flight flight = flightOptional.get();

      Mockito.when(flightRepository.findById(0))
              .thenReturn(flightOptional);
      Mockito.when(flightRepository.save(flight))
              .thenReturn(flight);

      assertEquals(flight, employeeService.updateFlight(0, flight));
  }

    @Test
    void updateSeats() {
        Optional<Seats> seatsOptional = MockUtil.getSeatOptional();
        Seats seats = seatsOptional.get();

        Mockito.when(seatRepository.findById(0))
                .thenReturn(seatsOptional);
        Mockito.when(seatRepository.save(seats))
                .thenReturn(seats);

        assertEquals(seats, employeeService.updateSeats(0, seats));
    }


}
