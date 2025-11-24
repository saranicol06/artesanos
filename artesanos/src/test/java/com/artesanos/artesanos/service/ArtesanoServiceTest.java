package com.artesanos.artesanos.service;

import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.repository.ArtesanoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Inicializa mocks automáticamente
class ArtesanoServiceTest {

    @Mock
    private ArtesanoRepository artesanoRepository;

    @InjectMocks
    private ArtesanoService artesanoService;

    private Artesano artesano1;
    private Artesano artesano2;

    @BeforeEach
    void setUp() {
        artesano1 = new Artesano();
        artesano1.setId(1L);
        artesano1.setNombre("Juan");
        artesano1.setUbicacion("La Guajira");
        artesano1.setTipoArtesania("Mochilas Wayuu");
        artesano1.setDescripcion("Artesano tradicional");

        artesano2 = new Artesano();
        artesano2.setId(2L);
        artesano2.setNombre("Maria");
        artesano2.setUbicacion("Córdoba");
        artesano2.setTipoArtesania("Sombreros Vueltiaos");
        artesano2.setDescripcion("Artesana de sombreros");
    }

    @Test
    void testGetAllArtesanos() {
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(artesano1, artesano2));

        List<Artesano> result = artesanoService.getAllArtesanos();

        assertEquals(2, result.size());
        verify(artesanoRepository, times(1)).findAll();
    }

    @Test
    void testGetArtesanoById() {
        when(artesanoRepository.findById(1L)).thenReturn(Optional.of(artesano1));

        Artesano result = artesanoService.getArtesanoById(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateArtesano() {
        when(artesanoRepository.save(artesano1)).thenReturn(artesano1);

        Artesano result = artesanoService.createArtesano(artesano1);

        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository, times(1)).save(artesano1);
    }

    @Test
    void testDeleteArtesano() {
        // Configuramos el mock para que exista el artesano
        when(artesanoRepository.existsById(1L)).thenReturn(true);

        // Llamamos al servicio
        boolean result = artesanoService.deleteArtesano(1L);

        // Verificamos que deleteById se llamó
        verify(artesanoRepository, times(1)).deleteById(1L);

        // Aseguramos que el método devuelva true
        assertTrue(result);
    }
}
