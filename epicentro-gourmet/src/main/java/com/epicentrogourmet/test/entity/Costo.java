
package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "costo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Costo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoPorSuperficie;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoPorMontaje;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal plusElectricidad;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal sueldoBase;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoAnioAntiguedad;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal plusCocinero;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal plusCocineroAyudante;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal plusLavaplatos;
}
