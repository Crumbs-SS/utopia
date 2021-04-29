package com.ss.utopia.repo;

import com.ss.utopia.entity.AirplaneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AirplaneTypeRepository extends JpaRepository<AirplaneType, Integer> {
}
