package com.ss.utopia.repo;

import com.ss.utopia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    Route findByOriAirportAndDesAirport(Airport ori, Airport des);
}
