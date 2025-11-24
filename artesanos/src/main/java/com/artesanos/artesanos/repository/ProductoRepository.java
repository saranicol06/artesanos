package com.artesanos.artesanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artesanos.artesanos.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí también puedes agregar consultas personalizadas
}

