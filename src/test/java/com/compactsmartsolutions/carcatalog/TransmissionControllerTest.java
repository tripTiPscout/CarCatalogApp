package com.compactsmartsolutions.carcatalog;

import com.compactsmartsolutions.carcatalog.entities.transmission.Transmission;
import com.compactsmartsolutions.carcatalog.entities.transmission.TransmissionController;
import com.compactsmartsolutions.carcatalog.entities.transmission.TransmissionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class TransmissionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransmissionService transmissionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        TransmissionController transmissionController = new TransmissionController(transmissionService);
        mockMvc = MockMvcBuilders.standaloneSetup(transmissionController).build();
    }

    @Test
    public void testCreateTransmission() throws Exception {
        Transmission transmission = new Transmission();
        transmission.setName("Automatic");

        when(transmissionService.createTransmission(any(Transmission.class))).thenReturn(transmission);

        mockMvc.perform(post("/transmissions/transmission")
                        .content("{ \"name\": \"Automatic\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Automatic"));
    }

    @Test
    public void testDeleteTransmission() throws Exception {
        mockMvc.perform(delete("/transmissions/1"))
                .andExpect(status().isNoContent());

        verify(transmissionService, times(1)).deleteTransmission(1L);
    }

    @Test
    public void testGetTransmissionById() throws Exception {
        Transmission transmission = new Transmission();
        transmission.setId(1L);
        transmission.setName("Automatic");
        when(transmissionService.getTransmissionById(1L)).thenReturn(transmission);

        mockMvc.perform(get("/transmissions/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Automatic"));
    }

    @Test
    public void testGetTransmissionByName() throws Exception {
        Transmission transmission = new Transmission();
        transmission.setId(1L);
        transmission.setName("Automatic");
        when(transmissionService.getTransmissionByName("Automatic")).thenReturn(transmission);

        mockMvc.perform(get("/transmissions/name/Automatic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Automatic"));
    }

    @Test
    public void testGetAllTransmissions() throws Exception {
        Transmission transmission1 = new Transmission();
        transmission1.setId(1L);
        transmission1.setName("Automatic");

        Transmission transmission2 = new Transmission();
        transmission2.setId(2L);
        transmission2.setName("Manual");

        List<Transmission> transmissions = Arrays.asList(transmission1, transmission2);
        when(transmissionService.getAllTransmissions()).thenReturn(transmissions);

        mockMvc.perform(get("/transmissions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Automatic"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Manual"));
    }

}
