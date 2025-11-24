package com.artesanos.artesanos.service;

import com.artesanos.artesanos.model.Artesano;
import com.artesanos.artesanos.repository.ArtesanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtesanoService {

    private final ArtesanoRepository artesanoRepository;

    public ArtesanoService(ArtesanoRepository artesanoRepository) {
        this.artesanoRepository = artesanoRepository;
    }

    public List<Artesano> getAllArtesanos() {
        return artesanoRepository.findAll();
    }

    public Artesano getArtesanoById(Long id) {
        return artesanoRepository.findById(id).orElse(null);
    }

    public Artesano createArtesano(Artesano artesano) {
        return artesanoRepository.save(artesano);
    }

    public Artesano updateArtesano(Artesano artesano) {
        if (artesano.getId() == null || !artesanoRepository.existsById(artesano.getId())) {
            return null;
        }
        return artesanoRepository.save(artesano);
    }

    public boolean deleteArtesano(Long id) {
        if (!artesanoRepository.existsById(id)) return false;
        artesanoRepository.deleteById(id);
        return true;
    }
}
