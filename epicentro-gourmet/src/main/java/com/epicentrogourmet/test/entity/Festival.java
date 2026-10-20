package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "festival")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String temporada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costo_id")
    private Costo costo;

    @ManyToMany
    private List<UnidadVenta> unidadesDeVenta = new ArrayList<>();

    @OneToMany(mappedBy = "festival")
    private List<Pedido> pedidos = new ArrayList<>();

    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin != null
                && fechaInicio != null
                && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        this.fechaFin = fechaFin;
    }
}