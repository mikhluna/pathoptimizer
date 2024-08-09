package com.example.pathoptimizer.service;

import com.example.pathoptimizer.model.Path;
import java.util.List;

public interface PathService {

    void addPath(Long id, Path path);
    List<Long> getShortestPath(Long sourceId, Long destinationId);
    Path getPathBetween(Long startId, Long endId);
    Path getPath(Long id);

}
