package com.example.pathoptimizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {

    private Long id;
    private Double cost;
    private Long sourceId;
    private Long destinationId;

}
