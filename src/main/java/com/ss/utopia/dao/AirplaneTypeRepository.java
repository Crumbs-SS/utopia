package com.ss.utopia.dao;

import com.ss.utopia.entity.AirplaneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirplaneTypeRepository extends JpaRepository<AirplaneType, Integer> {


}
