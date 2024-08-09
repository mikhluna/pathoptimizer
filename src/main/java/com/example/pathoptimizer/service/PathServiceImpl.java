package com.example.pathoptimizer.service;

import com.example.pathoptimizer.model.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathServiceImpl implements PathService {

    private final Map<Long, Path> paths = new HashMap<>();

    @Override
    public void addPath(Long id, Path path) {
        paths.put(id, path);
    }

    @Override
    public List<Long> getShortestPath(Long sourceId, Long destinationId) {
        // Implementación para obtener el camino más corto.
        return new ArrayList<>();
    }

    @Override
    public Path getPathBetween(Long startId, Long endId) {
        // Buscamos el camino entre startId y endId.
        return paths.values().stream()
                .filter(path -> path.getSourceId().equals(startId) && path.getDestinationId().equals(endId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Path getPath(Long id) {
        return paths.get(id); 
    }

}
