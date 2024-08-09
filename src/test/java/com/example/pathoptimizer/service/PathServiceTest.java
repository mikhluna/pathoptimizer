package com.example.pathoptimizer.service;

import com.example.pathoptimizer.model.Path;
import com.example.pathoptimizer.repository.PathRepository;
import com.example.pathoptimizer.repository.StationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class PathServiceTest {

    @Mock
    private PathRepository pathRepository;

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private PathService pathService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPath() {
        //Test añadiendo un path o camino
        Path path = new Path();
        path.setId(1L);
        path.setCost(100.0);
        path.setSourceId(1L); 
        path.setDestinationId(2L);

        when(pathRepository.save(path)).thenReturn(path);

    
    pathService.addPath(path.getId(), path); 

    //Vericamos que el path sea agregado
    Path result = pathRepository.findById(path.getId()).orElse(null);
    assertNotNull(result);
    assertEquals(100.0, result.getCost());
    assertEquals(1L, result.getSourceId());
    assertEquals(2L, result.getDestinationId());
    }

    @Test
    public void testGetPath() {
            //Devuelve un path
    Path path = new Path();
    path.setId(1L);
    path.setCost(100.0); 
    path.setSourceId(1L); 
    path.setDestinationId(2L); 

    when(pathRepository.findById(1L)).thenReturn(Optional.of(path));

    
    Path result = pathService.getPath(1L);

    
    assertNotNull(result); //Verifica no nulo
    assertEquals(100.0, result.getCost()); //Verifica costo
    assertEquals(1L, result.getSourceId()); //Verifica sourceId
    assertEquals(2L, result.getDestinationId()); //Verifica destinationId
    }

}
