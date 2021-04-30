package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.repo.AirportRepository;
import com.ss.utopia.repo.FlightRepository;
import com.ss.utopia.repo.SeatRepository;
import com.ss.utopia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AdminService {

    @Autowired AirportRepository airportRepository;
    @Autowired FlightRepository flightRepository;
    @Autowired SeatRepository seatRepository;
    @Autowired UserRepository userRepository;

    //Read Methods
    public List<Airport> getAirports(){ return airportRepository.findAll(); }
    public List<Flight> getFlights(){return flightRepository.findAll(); }
    public List<Seats> getSeats() { return seatRepository.findAll(); }
    public List<User> getTravelers(){return userRepository.getTravellers(); }
    public List<User> getEmployees(){return userRepository.getEmployees(); }

    //Add Methods
    public Airport addAirport(Airport airport){return airportRepository.save(airport);}
    public Flight addFlight(Flight flight){return flightRepository.save(flight); }
    public Seats addSeats(Seats seat){return seatRepository.save(seat); }
    public User addEmployee(User user){return userRepository.save(user); }
    public User addTraveler(User user){return userRepository.save(user); }

    //Delete Methods
    public void deleteAirport(String id){airportRepository.deleteById(id);}
    public void deleteFlight(Integer id){flightRepository.deleteById(id);}
    public void deleteSeats(Integer id){seatRepository.deleteById(id);}
    public void deleteEmployee(Integer id){userRepository.deleteEmployee(id);}
    public void deleteTraveler(Integer id){userRepository.deleteTraveler(id);}

    //update methods
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

    public String updateAirport(Airport a) {
        return null;
    }

    public String updateTraveler(User t) {
        return null;
    }

    public String updateEmployee(User e) {
        return null;
    }
}
