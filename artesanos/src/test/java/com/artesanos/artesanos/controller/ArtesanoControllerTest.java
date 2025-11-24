package com.artesanos.artesanos.controller;

import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.service.ArtesanoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtesanoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ArtesanoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtesanoService service;

    @Test
    void getAll_returns200() throws Exception {
        Artesano a = new Artesano();
        a.setId(1L);
        a.setNombre("Juan");

        when(service.getAllArtesanos()).thenReturn(List.of(a));

        mockMvc.perform(get("/api/artesanos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }
}
