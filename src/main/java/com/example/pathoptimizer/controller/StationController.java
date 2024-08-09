package com.example.pathoptimizer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pathoptimizer.model.Station;
import com.example.pathoptimizer.service.StationService;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @PutMapping("/{station_id}")
    public Map<String, String> putStation(@PathVariable Long station_id, @RequestBody Station station) {
        stationService.addStation(station_id, station);
        return Map.of("status", "ok");
    }
}
