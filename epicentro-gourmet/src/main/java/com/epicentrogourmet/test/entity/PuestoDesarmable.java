package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "puesto_desarmable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PuestoDesarmable extends UnidadVenta {

    private Integer cantidadCarpas;
    private Integer tiempoMontaje;
}