package com.epicentrogourmet.test.service;

import com.epicentrogourmet.test.entity.Festival;
import com.epicentrogourmet.test.repository.FestivalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalService {

    private final FestivalRepository repository;

    public FestivalService(FestivalRepository repository) {
        this.repository = repository;
    }

    public Festival agregar(Festival objeto) {
        return repository.save(objeto);
    }

    public void actualizar(Festival objeto) {
        repository.save(objeto);
    }

    public void eliminar(Festival objeto) {
        repository.delete(objeto);
    }

    public Festival traer(Long idFestival) {
        return repository.findById(idFestival).orElse(null);
    }

    public List<Festival> traer() {
        return repository.findAll();
    }
}