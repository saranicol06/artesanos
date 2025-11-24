package com.artesanos.artesanos.service;

import com.artesanos.artesanos.model.Producto;
import com.artesanos.artesanos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Producto producto) {
        if (producto.getId() == null || !productoRepository.existsById(producto.getId())) {
            return null;
        }
        return productoRepository.save(producto);
    }

    public boolean deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }
}
