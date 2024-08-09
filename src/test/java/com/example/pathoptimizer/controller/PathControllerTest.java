package com.example.pathoptimizer.controller;

import static org.mockito.Mockito.when;

import com.example.pathoptimizer.model.Path;
import com.example.pathoptimizer.service.PathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PathController.class)
public class PathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PathService pathService;

    @InjectMocks
    private PathController pathController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pathController).build();
    }

    @Test
    void testGetShortestPathSuccess() throws Exception {
        Long sourceId = 10L;
        Long destinationId = 13L;
        Path path = new Path(1L, 50.0, sourceId, destinationId);

        when(pathService.getShortestPath(sourceId, destinationId)).thenReturn(Collections.singletonList(sourceId));
        when(pathService.getPathBetween(sourceId, destinationId)).thenReturn(path);

        mockMvc.perform(MockMvcRequestBuilders.get("/paths/{source_id}/{destination_id}", sourceId, destinationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(50.0));
    }

    @Test
    void testGetShortestPathNotFound() throws Exception {
        Long sourceId = 10L;
        Long destinationId = 13L;

        when(pathService.getShortestPath(sourceId, destinationId)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/paths/{source_id}/{destination_id}", sourceId, destinationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Path not found"));
    }

    @Test
    void testGetShortestPathInternalError() throws Exception {
        Long sourceId = 10L;
        Long destinationId = 13L;

        when(pathService.getShortestPath(sourceId, destinationId)).thenReturn(Collections.singletonList(sourceId));
        when(pathService.getPathBetween(sourceId, destinationId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/paths/{source_id}/{destination_id}", sourceId, destinationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Path data missing"));
    }

}
