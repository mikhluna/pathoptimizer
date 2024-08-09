package com.example.pathoptimizer.service;

import com.example.pathoptimizer.model.Station;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class StationServiceImpl implements StationService{

    private final Map<Long, Station> stations = new HashMap<>();

    @Override
    public void addStation(Long stationId, Station station) {
        stations.put(stationId, station);
    }

    @Override
    public Optional<Station> getStation(Long id) {
        return Optional.ofNullable(stations.get(id));
    }

}
