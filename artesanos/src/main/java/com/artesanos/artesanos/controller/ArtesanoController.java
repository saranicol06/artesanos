package com.artesanos.artesanos.controller;

import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.service.ArtesanoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artesanos")
public class ArtesanoController {

    private final ArtesanoService service;

    public ArtesanoController(ArtesanoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Artesano> getAll() {
        return service.getAllArtesanos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artesano> getById(@PathVariable Long id) {
        Artesano a = service.getArtesanoById(id);
        return (a != null) ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Artesano create(@RequestBody Artesano a) {
        return service.createArtesano(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artesano> update(@PathVariable Long id, @RequestBody Artesano a) {
        a.setId(id);
        Artesano updated = service.updateArtesano(a);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteArtesano(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
