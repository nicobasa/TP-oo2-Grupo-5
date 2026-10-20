package com.epicentrogourmet.test.service;

import com.epicentrogourmet.test.entity.Plato;
import com.epicentrogourmet.test.repository.PlatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService {

    private final PlatoRepository repository;

    public PlatoService(PlatoRepository repository) {
        this.repository = repository;
    }

    public Plato agregar(Plato objeto) {
        return repository.save(objeto);
    }

    public void actualizar(Plato objeto) {
        repository.save(objeto);
    }

    public void eliminar(Plato objeto) {
        repository.delete(objeto);
    }

    public Plato traer(Long idPlato) {
        return repository.findById(idPlato).orElse(null);
    }

    public List<Plato> traer() {
        return repository.findAll();
    }
}