
package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private Long dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private Double sueldoBase;
    @Column(nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "unidad_venta_id")
    private UnidadVenta unidadVenta;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser nula");
        }

        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException(
                    "El empleado debe ser mayor de edad");
        }

        this.fechaNacimiento = fechaNacimiento;
    }

    public int calcularAntiguedad() {

        if (fechaIngreso == null) {
            return 0;
        }

        return Period.between(
                fechaIngreso,
                LocalDate.now()
        ).getYears();
    }
}
