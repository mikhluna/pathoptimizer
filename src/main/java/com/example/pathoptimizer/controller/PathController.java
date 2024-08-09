package com.example.pathoptimizer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pathoptimizer.model.Path;
import com.example.pathoptimizer.service.PathService;

@RestController
@RequestMapping("/paths")
public class PathController {

    @Autowired
    private PathService pathService;

    @GetMapping("/{source_id}/{destination_id}")
    public ResponseEntity<Map<String, Object>> getShortestPath(@PathVariable("source_id") Long sourceId,
                                                                @PathVariable("destination_id") Long destinationId) {
        // Validamos que los IDs de las estaciones son válidos
        List<Long> path = pathService.getShortestPath(sourceId, destinationId);
        
        if (path == null || path.isEmpty()) {
            // Manejo estado en caso que no se encuentra un camino
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Path not found"));
        }

        double cost = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            Long start = path.get(i);
            Long end = path.get(i + 1);
            Path p = pathService.getPathBetween(start, end);
            
            if (p == null) {
                 // Manejo caso donde no se encuentre un camino entre estaciones
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Path data missing"));
            }
            cost += p.getCost();
        }

         // Devuelvo resultado OK
        return ResponseEntity.ok(Map.of("path", path, "cost", cost));
    }

}
