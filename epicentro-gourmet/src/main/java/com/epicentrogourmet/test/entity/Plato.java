package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "plato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double precioVenta;

    private Double costoProduccion;

    @OneToMany(mappedBy = "plato")
    private List<DetallePedido> detalles;
}