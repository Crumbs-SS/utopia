package com.ss.utopia.repo;

import com.ss.utopia.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seats, Integer> {
}
