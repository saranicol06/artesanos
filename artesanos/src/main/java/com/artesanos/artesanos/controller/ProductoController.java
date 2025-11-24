package com.artesanos.artesanos.controller;

import com.artesanos.artesanos.model.Producto;
import com.artesanos.artesanos.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> getAll() {
        return service.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        Producto p = service.getProductoById(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Producto create(@RequestBody Producto p) {
        return service.createProducto(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto p) {
        p.setId(id);
        Producto updated = service.updateProducto(p);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteProducto(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
