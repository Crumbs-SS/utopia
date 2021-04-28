package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

    @Query("SELECT at FROM airplane at WHERE at.airplaneType.maxCapacity = ?1")
    List<Airplane> findAirplanesByMaxCapacity(Integer maxCapacity);
}

