package com.artesanos.artesanos.config;

import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.model.Producto;
import com.artesanos.artesanos.service.ArtesanoService;
import com.artesanos.artesanos.service.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ArtesanoService artesanoService;
    private final ProductoService productoService;

    public DataLoader(ArtesanoService artesanoService, ProductoService productoService) {
        this.artesanoService = artesanoService;
        this.productoService = productoService;
    }

    @Override
    public void run(String... args) throws Exception {
        Artesano a1 = new Artesano();
        a1.setNombre("Sara");
        a1.setUbicacion("Bogotá");
        a1.setTipoArtesania("Flores");
        a1.setDescripcion("Flores eternas");
        artesanoService.createArtesano(a1);

        Producto p1 = new Producto();
        p1.setNombre("Rosa eterna");
        p1.setDescripcion("Rosa preservada en cristal");
        p1.setPrecio(25.0);
        p1.setCantidad(10);
        p1.setArtesano(a1);
        productoService.createProducto(p1);
    }
}
