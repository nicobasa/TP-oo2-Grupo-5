package com.epicentrogourmet.test.service;

import com.epicentrogourmet.test.entity.Empleado;
import com.epicentrogourmet.test.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado agregar(Empleado objeto) {
        return repository.save(objeto);
    }

    public void actualizar(Empleado objeto) {
        repository.save(objeto);
    }

    public void eliminar(Empleado objeto) {
        repository.delete(objeto);
    }

    public Empleado traer(Long idEmpleado) {
        return repository.findById(idEmpleado).orElse(null);
    }

    public List<Empleado> traer() {
        return repository.findAll();
    }
}