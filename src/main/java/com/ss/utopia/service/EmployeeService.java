package com.ss.utopia.service;

import com.ss.utopia.entity.*;
import com.ss.utopia.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(rollbackFor = { Exception.class })
public class EmployeeService {

    @Autowired FlightRepository flightRepository;
    @Autowired SeatRepository seatRepository;
    @Autowired AirplaneRepository airplaneRepository;
    @Autowired AirportRepository airportRepository;
    @Autowired RouteRepository routeRepository;

    public Flight getFlight(Integer id){
        try {
            return flightRepository.findById(id).get();
        } catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public List<Seats> getSeats() {
        try {
            return seatRepository.findAll();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }
    //update methods
    public Flight updateFlight(int id, Flight flight) {
        try{
            flight.setId(id);
            Flight temp = flightRepository.findById(flight.getId()).get();

            // just to check if the string was a valid datetime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime timestamp = LocalDateTime.parse(flight.getDepartTime(), formatter);

            Airplane airplane = airplaneRepository.findById(flight.getAirplane().getId()).orElseThrow();
            flight.setAirplane(airplane);
            Airport ori = airportRepository
                    .findById(flight.getRoute().getOriAirport().getAirportCode()).orElseThrow();
            Airport des = airportRepository
                    .findById(flight.getRoute().getDesAirport().getAirportCode()).orElseThrow();
            Route route = routeRepository.findByOriAirportAndDesAirport(ori, des);
            if (null == route) {
                route = routeRepository.save(new Route(ori, des));
            }
            flight.setRoute(route);
            return flightRepository.save(flight);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Seats updateSeats(int id, Seats seat) {
        try {
            seat.setId(id);
            Seats temp = seatRepository.findById(seat.getId()).get();
            seat.setFlight(temp.getFlight());
            if (seat.getFirst() == null) seat.setFirst(temp.getFirst());
            if (seat.getBusiness() == null) seat.setBusiness(temp.getBusiness());
            if (seat.getEconomy() == null) seat.setEconomy(temp.getEconomy());
            return seatRepository.save(seat);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }
/*
public ResponseEntity<Flight> getFlight(Integer id){
        try {
            return new ResponseEntity(flightRepository.findById(id).get(), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getCause(), HttpStatus.CONFLICT);
        }
    }
    public ResponseEntity<List<Seats>> getSeats() {
        try {
            return new ResponseEntity(seatRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getCause(), HttpStatus.CONFLICT);
        }
    }
    public ResponseEntity updateFlight(Flight flight)  {

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
            return new ResponseEntity(e.getCause(),HttpStatus.NOT_FOUND);
            }
        catch(Exception e){
            return new ResponseEntity(e.getCause(),HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity("Update Successful", HttpStatus.OK);
    }

    public ResponseEntity updateSeats(Seats seat) {
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
            return new ResponseEntity(e.getCause(),HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getCause(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Update Successful", HttpStatus.OK);
    }*/

}