package com.epicentrogourmet.test.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food_truck")
@Getter
@Setter
@NoArgsConstructor
public class FoodTruck extends UnidadVenta{

    private String patente;
    private boolean requiereElectricidad;
}
