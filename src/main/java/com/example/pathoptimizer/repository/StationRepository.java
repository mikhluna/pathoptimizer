package com.example.pathoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathoptimizer.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}
