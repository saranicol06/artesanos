package com.artesanos.artesanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artesanos.artesanos.model.Artesano;

@Repository
public interface ArtesanoRepository extends JpaRepository<Artesano, Long> {
    // Aquí puedes agregar consultas personalizadas si quieres
}
