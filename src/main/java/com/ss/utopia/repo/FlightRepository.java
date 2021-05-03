package com.ss.utopia.repo;

import com.ss.utopia.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
