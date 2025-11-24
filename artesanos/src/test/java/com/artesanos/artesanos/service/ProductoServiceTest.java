package com.artesanos.artesanos.service;

import com.artesanos.artesanos.model.Producto;
import com.artesanos.artesanos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Mochila Wayuu");
    }

    @Test
    void testDeleteProducto() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1L);

        productoService.deleteProducto(1L);

        verify(productoRepository, times(1)).existsById(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
