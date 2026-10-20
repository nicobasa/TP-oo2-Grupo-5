package com.epicentrogourmet.test.repository;

import com.epicentrogourmet.test.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}