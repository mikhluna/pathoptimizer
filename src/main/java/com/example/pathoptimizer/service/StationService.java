package com.example.pathoptimizer.service;

import com.example.pathoptimizer.model.Station;
import java.util.Optional;

public interface StationService {

    Optional<Station> getStation(Long id); //
    void addStation(Long stationid, Station station);

}
