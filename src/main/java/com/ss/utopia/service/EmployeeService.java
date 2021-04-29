package com.ss.utopia.service;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Seats;
import com.ss.utopia.repo.FlightRepository;
import com.ss.utopia.repo.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(rollbackFor = { Exception.class })
public class EmployeeService {

    @Autowired FlightRepository flightRepository;
    @Autowired SeatRepository seatRepository;

    public Flight getFlight(Integer id){
        return flightRepository.findById(id).get();
    }
    public List<Seats> getSeats() {
        return seatRepository.findAll();
    }

    public String updateFlight(Flight flight)  {

        try {
            if (flight.getId() == null) throw new Exception();

            Flight temp = flightRepository.findById(flight.getId()).get();

            if (flight.getRoute() == null) flight.setRoute(temp.getRoute());

            if (flight.getAirplane() == null) flight.setAirplane(temp.getAirplane());

            if (flight.getDepartTime() == null) flight.setDepartTime(temp.getDepartTime());

            if (flight.getReservedSeats() == null) flight.setReservedSeats(temp.getReservedSeats());

            if (flight.getSeatPrice() == null) flight.setSeatPrice(temp.getSeatPrice());

            flightRepository.save(flight);
        }
        catch(NoSuchElementException e){
                return "no flight with this id is present, unable to perform update";
            }
        catch(Exception e){
                return "id was not provided, unable to perform update";
            }
        return "update successful";
    }

    public String updateSeats(Seats seat) {
        try{
            if(seat.getId() == null) throw new Exception();

            Seats temp = seatRepository.findById(seat.getId()).get();

            if(seat.getFlight() == null) seat.setFlight(flightRepository.findById(seat.getId()).get());

            if(seat.getFirst() == null) seat.setFirst(temp.getFirst());

            if(seat.getBusiness() == null) seat.setBusiness(temp.getBusiness());

            if(seat.getEconomy() == null) seat.setEconomy(temp.getEconomy());

            seatRepository.save(seat);
        }
        catch(NoSuchElementException e){
            return "no flight with this id is present, unable to perform update";
        }
        catch(Exception e){
            return "id was not provided, unable to perform update";
        }

        return "update successful";
    }

}