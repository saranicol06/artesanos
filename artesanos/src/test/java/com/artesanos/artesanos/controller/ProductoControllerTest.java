package com.artesanos.artesanos.controller;

import com.artesanos.artesanos.model.Producto;
import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.service.ProductoService;
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

@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService service;

    @Test
    void getAll_returns200() throws Exception {
        Artesano a = new Artesano();
        a.setId(1L);
        a.setNombre("Juan");

        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Pizza");
        p.setDescripcion("Pizza artesanal");
        p.setPrecio(25.0);
        p.setCantidad(10);
        p.setArtesano(a);

        when(service.getAllProductos()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nombre").value("Pizza"));
    }
}
