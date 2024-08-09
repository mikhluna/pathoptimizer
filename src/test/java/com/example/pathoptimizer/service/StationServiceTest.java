package com.example.pathoptimizer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.pathoptimizer.model.Station;
import com.example.pathoptimizer.repository.StationRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationServiceImpl stationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStation() {
        
        Station station = new Station();
        station.setId(1L);
        station.setName("Station A");

        stationService.addStation(station.getId(), station);

        verify(stationRepository).save(station);
    }

    @Test
    public void testGetStation() {
    
        Station station = new Station();
        station.setId(1L);
        station.setName("Station A");

        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));

        Optional<Station> result = stationService.getStation(1L);
        assertEquals("Station A", result.get().getName());
    }

}
