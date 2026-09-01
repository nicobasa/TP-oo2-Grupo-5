package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datos.Cajero;
import datos.Cocinero;
import datos.DetallePedido;
import datos.Empleado;
import datos.Festival;
import datos.FoodTruck;
import datos.Pedido;
import datos.Plato;
import datos.PuestoDesarmable;
import datos.UnidadVenta;
import negocio.DetallePedidoABM;
import negocio.EmpleadoABM;
import negocio.FestivalABM;
import negocio.PedidoABM;
import negocio.PlatoABM;
import negocio.UnidadVentaABM;

public class TestCargarBD {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       CARGA DE DATOS DE PRUEBA");
        System.out.println("========================================");

        FestivalABM festivalABM = new FestivalABM();
        UnidadVentaABM unidadVentaABM = new UnidadVentaABM();
        EmpleadoABM empleadoABM = new EmpleadoABM();
        PlatoABM platoABM = new PlatoABM();
        PedidoABM pedidoABM = new PedidoABM();
        DetallePedidoABM detallePedidoABM =
                new DetallePedidoABM();

        // =====================================================
        // 1. FESTIVALES
        // =====================================================

        List<Festival> festivales =
                festivalABM.traer();

        Festival festival1 =
                obtenerOCrearFestival(
                        festivalABM,
                        festivales,
                        "Festival de la Primavera",
                        "Primavera",
                        LocalDate.of(2026, 9, 21),
                        LocalDate.of(2026, 9, 27),
                        180,
                        4000,
                        2000,
                        450000);

        Festival festival2 =
                obtenerOCrearFestival(
                        festivalABM,
                        festivales,
                        "Festival del Verano",
                        "Verano",
                        LocalDate.of(2026, 12, 15),
                        LocalDate.of(2026, 12, 30),
                        160,
                        4000,
                        2000,
                        470000);

        Festival festival3 =
                obtenerOCrearFestival(
                        festivalABM,
                        festivales,
                        "Festival Sabores de Buenos Aires",
                        "Otono",
                        LocalDate.of(2027, 3, 10),
                        LocalDate.of(2027, 3, 20),
                        180,
                        4500,
                        2500,
                        500000);

        // =====================================================
        // 2. UNIDADES DE VENTA
        // =====================================================

        List<UnidadVenta> unidades =
                unidadVentaABM.traer();

        FoodTruck unidad1 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000001",
                        "Hamburguesas del Sur",
                        20.5,
                        "AA123BB",
                        true);

        FoodTruck unidad2 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000002",
                        "Papas & Algo",
                        18.0,
                        "AC456CD",
                        false);

        PuestoDesarmable unidad3 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000001",
                        "Sabores Criollos",
                        30.0,
                        3,
                        120);

        PuestoDesarmable unidad4 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000002",
                        "Dulce Tentacion",
                        25.0,
                        2,
                        90);

        FoodTruck unidad5 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000003",
                        "Pizza Express",
                        19.0,
                        "AD111EF",
                        true);

        FoodTruck unidad6 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000004",
                        "Tacos del Barrio",
                        21.0,
                        "AE222FG",
                        true);

        FoodTruck unidad7 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000005",
                        "La Parrilla Movil",
                        22.5,
                        "AF333GH",
                        false);

        FoodTruck unidad8 =
                obtenerOCrearFoodTruck(
                        unidadVentaABM,
                        unidades,
                        "FT00000006",
                        "Wok Oriental",
                        17.5,
                        "AG444HI",
                        true);

        PuestoDesarmable unidad9 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000003",
                        "Pastas Caseras",
                        28.0,
                        3,
                        100);

        PuestoDesarmable unidad10 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000004",
                        "Arepas Venezuela",
                        26.0,
                        2,
                        80);

        PuestoDesarmable unidad11 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000005",
                        "Postres Artesanales",
                        24.0,
                        2,
                        75);

        PuestoDesarmable unidad12 =
                obtenerOCrearPuesto(
                        unidadVentaABM,
                        unidades,
                        "PD00000006",
                        "Sabores del Norte",
                        32.0,
                        4,
                        140);

        // =====================================================
        // 3. EMPLEADOS
        // =====================================================

        List<Empleado> empleados =
                empleadoABM.traer();

        Cajero empleado1 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Juan",
                        "Perez",
                        30111222,
                        LocalDate.of(1990, 5, 10),
                        LocalDate.of(2023, 3, 1),
                        450000,
                        "Manana",
                        unidad1);

        Cajero empleado2 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Maria",
                        "Gomez",
                        31222333,
                        LocalDate.of(1992, 8, 20),
                        LocalDate.of(2024, 2, 15),
                        470000,
                        "Noche",
                        unidad2);

        Cocinero empleado3 =
                obtenerOCrearCocinero(
                        empleadoABM,
                        empleados,
                        "Carlos",
                        "Rodriguez",
                        32333444,
                        LocalDate.of(1988, 1, 15),
                        LocalDate.of(2022, 6, 1),
                        550000,
                        "Parrilla",
                        80000,
                        unidad3);

        Cocinero empleado4 =
                obtenerOCrearCocinero(
                        empleadoABM,
                        empleados,
                        "Laura",
                        "Fernandez",
                        33444555,
                        LocalDate.of(1995, 11, 3),
                        LocalDate.of(2024, 4, 10),
                        530000,
                        "Pasteleria",
                        70000,
                        unidad4);

        Cajero empleado5 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Pedro",
                        "Lopez",
                        34555666,
                        LocalDate.of(1991, 2, 12),
                        LocalDate.of(2025, 1, 10),
                        460000,
                        "Manana",
                        unidad5);

        Cajero empleado6 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Sofia",
                        "Martinez",
                        35666777,
                        LocalDate.of(1993, 7, 8),
                        LocalDate.of(2025, 2, 5),
                        480000,
                        "Noche",
                        unidad6);

        Cajero empleado7 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Diego",
                        "Sanchez",
                        36777888,
                        LocalDate.of(1989, 9, 21),
                        LocalDate.of(2024, 8, 12),
                        475000,
                        "Noche",
                        unidad7);

        Cajero empleado8 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Valentina",
                        "Diaz",
                        37888999,
                        LocalDate.of(1996, 4, 17),
                        LocalDate.of(2025, 3, 2),
                        465000,
                        "Manana",
                        unidad8);

        Cajero empleado9 =
                obtenerOCrearCajero(
                        empleadoABM,
                        empleados,
                        "Martin",
                        "Torres",
                        38999000,
                        LocalDate.of(1990, 12, 1),
                        LocalDate.of(2024, 10, 20),
                        490000,
                        "Noche",
                        unidad9);

        Cocinero empleado10 =
                obtenerOCrearCocinero(
                        empleadoABM,
                        empleados,
                        "Federico",
                        "Alvarez",
                        39000111,
                        LocalDate.of(1987, 3, 14),
                        LocalDate.of(2022, 5, 4),
                        570000,
                        "Cocina Internacional",
                        90000,
                        unidad10);

        Cocinero empleado11 =
                obtenerOCrearCocinero(
                        empleadoABM,
                        empleados,
                        "Camila",
                        "Romero",
                        40111222,
                        LocalDate.of(1994, 6, 25),
                        LocalDate.of(2024, 1, 15),
                        540000,
                        "Pasteleria",
                        75000,
                        unidad11);

        Cocinero empleado12 =
                obtenerOCrearCocinero(
                        empleadoABM,
                        empleados,
                        "Nicolas",
                        "Molina",
                        41222333,
                        LocalDate.of(1986, 10, 9),
                        LocalDate.of(2021, 11, 8),
                        590000,
                        "Parrilla",
                        100000,
                        unidad12);

        obtenerOCrearCocinero(
                empleadoABM,
                empleados,
                "Agustina",
                "Castro",
                42333444,
                LocalDate.of(1997, 1, 30),
                LocalDate.of(2025, 4, 1),
                525000,
                "Cocina Vegetariana",
                60000,
                unidad5);

        obtenerOCrearCajero(
                empleadoABM,
                empleados,
                "Lucas",
                "Moreno",
                43444555,
                LocalDate.of(1992, 5, 19),
                LocalDate.of(2024, 7, 10),
                485000,
                "Noche",
                unidad6);

        obtenerOCrearCocinero(
                empleadoABM,
                empleados,
                "Julieta",
                "Rojas",
                44555666,
                LocalDate.of(1995, 8, 11),
                LocalDate.of(2025, 5, 15),
                535000,
                "Cocina Mediterranea",
                65000,
                unidad7);

        obtenerOCrearCajero(
                empleadoABM,
                empleados,
                "Tomas",
                "Navarro",
                45666777,
                LocalDate.of(1988, 11, 27),
                LocalDate.of(2023, 9, 18),
                495000,
                "Manana",
                unidad8);

        // RESPONSABLES

        unidad1.setResponsable(empleado1);
        unidad2.setResponsable(empleado2);
        unidad3.setResponsable(empleado3);
        unidad4.setResponsable(empleado4);
        unidad5.setResponsable(empleado5);
        unidad6.setResponsable(empleado6);
        unidad7.setResponsable(empleado7);
        unidad8.setResponsable(empleado8);
        unidad9.setResponsable(empleado9);
        unidad10.setResponsable(empleado10);
        unidad11.setResponsable(empleado11);
        unidad12.setResponsable(empleado12);

        actualizarUnidades(
                unidadVentaABM,
                unidad1, unidad2, unidad3, unidad4,
                unidad5, unidad6, unidad7, unidad8,
                unidad9, unidad10, unidad11, unidad12);

        // =====================================================
        // 4. PLATOS
        // =====================================================

        List<Plato> platos =
                platoABM.traer();

        Plato plato1 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Hamburguesa Clasica", 6500, 2800);

        Plato plato2 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Hamburguesa Completa", 7500, 3400);

        Plato plato3 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Papas Fritas", 3500, 1200);

        Plato plato4 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Choripan", 4500, 1800);

        Plato plato5 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Empanada de Carne", 2500, 900);

        Plato plato6 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Empanada de Humita", 2500, 850);

        Plato plato7 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Torta de Chocolate", 5000, 2200);

        Plato plato8 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Cheesecake", 5500, 2500);

        Plato plato9 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Pizza Muzzarella", 6000, 2500);

        Plato plato10 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Pizza Napolitana", 7000, 3000);

        Plato plato11 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Tacos de Carne", 5500, 2300);

        Plato plato12 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Tacos Vegetarianos", 5000, 2100);

        Plato plato13 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Pollo al Wok", 6500, 2800);

        Plato plato14 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Fideos Salteados", 6000, 2400);

        Plato plato15 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Ravioles Caseros", 7000, 2900);

        Plato plato16 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Lasagna", 7500, 3200);

        Plato plato17 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Arepa de Pollo", 4500, 1800);

        Plato plato18 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Brownie", 4000, 1600);

        Plato plato19 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Flan Casero", 3500, 1200);

        Plato plato20 =
                obtenerOCrearPlato(
                        platoABM, platos,
                        "Chocotorta", 5000, 2200);

        // =====================================================
        // 5. UNIDAD - FESTIVAL
        // =====================================================

        unidad1.setFestivales(
                lista(festival1, festival2));

        unidad2.setFestivales(
                lista(festival1, festival3));

        unidad3.setFestivales(
                lista(festival1, festival2, festival3));

        unidad4.setFestivales(
                lista(festival2, festival3));

        unidad5.setFestivales(
                lista(festival1, festival3));

        unidad6.setFestivales(
                lista(festival1, festival2));

        unidad7.setFestivales(
                lista(festival2, festival3));

        unidad8.setFestivales(
                lista(festival1, festival2, festival3));

        unidad9.setFestivales(
                lista(festival1, festival3));

        unidad10.setFestivales(
                lista(festival1, festival2));

        unidad11.setFestivales(
                lista(festival2, festival3));

        unidad12.setFestivales(
                lista(festival1, festival2, festival3));

        // =====================================================
        // 6. UNIDAD - PLATO
        // =====================================================

        unidad1.setPlatos(
                lista(plato1, plato2, plato3));

        unidad2.setPlatos(
                lista(plato2, plato3, plato4));

        unidad3.setPlatos(
                lista(plato4, plato5, plato6));

        unidad4.setPlatos(
                lista(plato5, plato6, plato7, plato8));

        unidad5.setPlatos(
                lista(plato1, plato9, plato10));

        unidad6.setPlatos(
                lista(plato2, plato11, plato12));

        unidad7.setPlatos(
                lista(plato3, plato4, plato13));

        unidad8.setPlatos(
                lista(plato5, plato6, plato14));

        unidad9.setPlatos(
                lista(plato7, plato15, plato16));

        unidad10.setPlatos(
                lista(plato8, plato17, plato18));

        unidad11.setPlatos(
                lista(plato19, plato20, plato1));

        unidad12.setPlatos(
                lista(plato2, plato7, plato8));

        actualizarUnidades(
                unidadVentaABM,
                unidad1, unidad2, unidad3, unidad4,
                unidad5, unidad6, unidad7, unidad8,
                unidad9, unidad10, unidad11, unidad12);

        // =====================================================
        // 7. PEDIDOS
        // =====================================================

        List<Pedido> pedidos =
                pedidoABM.traer();

        Pedido pedido1 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 21, 12, 30),
                festival1, unidad1);

        Pedido pedido2 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 21, 13, 15),
                festival1, unidad1);

        Pedido pedido3 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 22, 14, 0),
                festival1, unidad2);

        Pedido pedido4 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 23, 19, 30),
                festival1, unidad3);

        Pedido pedido5 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 24, 12, 10),
                festival1, unidad5);

        Pedido pedido6 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 24, 13, 20),
                festival1, unidad6);

        Pedido pedido7 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 25, 19, 15),
                festival1, unidad8);

        Pedido pedido8 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 26, 20, 10),
                festival1, unidad9);

        Pedido pedido9 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 26, 21, 0),
                festival1, unidad10);

        Pedido pedido10 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 9, 27, 18, 30),
                festival1, unidad12);

        Pedido pedido11 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 16, 20, 15),
                festival2, unidad1);

        Pedido pedido12 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 17, 21, 0),
                festival2, unidad3);

        Pedido pedido13 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 18, 13, 40),
                festival2, unidad6);

        Pedido pedido14 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 19, 14, 10),
                festival2, unidad7);

        Pedido pedido15 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 19, 19, 45),
                festival2, unidad8);

        Pedido pedido16 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 21, 21, 10),
                festival2, unidad10);

        Pedido pedido17 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 22, 13, 0),
                festival2, unidad11);

        Pedido pedido18 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 23, 18, 50),
                festival2, unidad12);

        Pedido pedido19 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 24, 12, 30),
                festival2, unidad4);

        Pedido pedido20 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2026, 12, 26, 20, 0),
                festival2, unidad7);

        Pedido pedido21 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 12, 12, 45),
                festival3, unidad2);

        Pedido pedido22 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 13, 18, 30),
                festival3, unidad4);

        Pedido pedido23 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 14, 12, 15),
                festival3, unidad5);

        Pedido pedido24 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 15, 14, 20),
                festival3, unidad7);

        Pedido pedido25 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 16, 19, 10),
                festival3, unidad8);

        Pedido pedido26 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 17, 20, 40),
                festival3, unidad11);

        Pedido pedido27 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 18, 21, 20),
                festival3, unidad12);

        Pedido pedido28 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 19, 13, 10),
                festival3, unidad9);

        Pedido pedido29 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 19, 18, 0),
                festival3, unidad3);

        Pedido pedido30 = obtenerOCrearPedido(
                pedidoABM, pedidos,
                LocalDateTime.of(2027, 3, 20, 19, 0),
                festival3, unidad5);

        // =====================================================
        // 8. DETALLES
        // =====================================================

        agregarDosDetalles(
                detallePedidoABM,
                pedido1,
                plato1, 2,
                plato3, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido2,
                plato2, 1,
                plato3, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido3,
                plato2, 1,
                plato4, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido4,
                plato4, 2,
                plato6, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido5,
                plato9, 2,
                plato10, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido6,
                plato11, 2,
                plato12, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido7,
                plato5, 2,
                plato14, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido8,
                plato15, 1,
                plato16, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido9,
                plato17, 2,
                plato18, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido10,
                plato2, 1,
                plato7, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido11,
                plato2, 1,
                plato3, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido12,
                plato5, 3,
                plato6, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido13,
                plato11, 1,
                plato12, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido14,
                plato3, 2,
                plato13, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido15,
                plato6, 1,
                plato14, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido16,
                plato8, 1,
                plato17, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido17,
                plato19, 2,
                plato20, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido18,
                plato7, 1,
                plato8, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido19,
                plato5, 2,
                plato7, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido20,
                plato4, 1,
                plato13, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido21,
                plato3, 2,
                plato4, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido22,
                plato6, 2,
                plato8, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido23,
                plato1, 1,
                plato10, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido24,
                plato4, 2,
                plato13, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido25,
                plato5, 1,
                plato14, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido26,
                plato19, 1,
                plato20, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido27,
                plato2, 2,
                plato8, 1);

        agregarDosDetalles(
                detallePedidoABM,
                pedido28,
                plato7, 1,
                plato15, 2);

        agregarDosDetalles(
                detallePedidoABM,
                pedido29,
                plato4, 1,
                plato6, 3);

        agregarDosDetalles(
                detallePedidoABM,
                pedido30,
                plato9, 2,
                plato10, 1);

        // =====================================================
        // RESULTADO
        // =====================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("       CARGA FINALIZADA");
        System.out.println("========================================");

        System.out.println(
                "Festivales: "
                + festivalABM.traer().size());

        System.out.println(
                "Unidades de venta: "
                + unidadVentaABM.traer().size());

        System.out.println(
                "Empleados: "
                + empleadoABM.traer().size());

        System.out.println(
                "Platos: "
                + platoABM.traer().size());

        System.out.println(
                "Pedidos: "
                + pedidoABM.traer().size());

        System.out.println(
                "Detalles de pedido: "
                + detallePedidoABM.traer().size());

        System.out.println("========================================");
        System.out.println("OK");
    }

    // =====================================================
    // METODOS AUXILIARES
    // =====================================================

    private static Festival obtenerOCrearFestival(
            FestivalABM abm,
            List<Festival> existentes,
            String nombre,
            String temporada,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            double costoPorSuperficie,
            double costoPorMontaje,
            double plusUsoElectricidad,
            double sueldoBase) {

        Festival festival =
                buscarFestival(existentes, nombre);

        if (festival == null) {

            festival = new Festival(
                    nombre,
                    temporada,
                    fechaInicio,
                    fechaFin,
                    costoPorSuperficie,
                    costoPorMontaje,
                    plusUsoElectricidad,
                    sueldoBase);

            abm.agregar(festival);
            existentes.add(festival);

        } else {

            // Si el festival ya existe, actualizamos sus datos de prueba
            // para que el test pueda ejecutarse nuevamente sin recrear la BD.
            festival.setTemporada(temporada);
            festival.setFechaInicio(fechaInicio);
            festival.setFechaFin(fechaFin);
            festival.setCostoPorSuperficie(costoPorSuperficie);
            festival.setCostoPorMontaje(costoPorMontaje);
            festival.setPlusUsoElectricidad(plusUsoElectricidad);
            festival.setSueldoBase(sueldoBase);

            abm.actualizar(festival);
        }

        return festival;
    }

    private static FoodTruck obtenerOCrearFoodTruck(
            UnidadVentaABM abm,
            List<UnidadVenta> existentes,
            String codigo,
            String nombre,
            double superficie,
            String patente,
            boolean requiereElectricidad) {

        UnidadVenta existente =
                buscarUnidad(existentes, codigo);

        if (existente != null) {
            return (FoodTruck) existente;
        }

        FoodTruck unidad =
                new FoodTruck();

        unidad.setCodigo(codigo);
        unidad.setNombreComercial(nombre);
        unidad.setSuperficie(superficie);
        unidad.setPatente(patente);
        unidad.setRequiereElectricidad(
                requiereElectricidad);

        abm.agregar(unidad);
        existentes.add(unidad);

        return unidad;
    }

    private static PuestoDesarmable obtenerOCrearPuesto(
            UnidadVentaABM abm,
            List<UnidadVenta> existentes,
            String codigo,
            String nombre,
            double superficie,
            int cantidadCarpas,
            int tiempoMontaje) {

        UnidadVenta existente =
                buscarUnidad(existentes, codigo);

        if (existente != null) {
            return (PuestoDesarmable) existente;
        }

        PuestoDesarmable unidad =
                new PuestoDesarmable();

        unidad.setCodigo(codigo);
        unidad.setNombreComercial(nombre);
        unidad.setSuperficie(superficie);
        unidad.setCantidadCarpas(cantidadCarpas);
        unidad.setTiempoMontaje(tiempoMontaje);

        abm.agregar(unidad);
        existentes.add(unidad);

        return unidad;
    }

    private static Cajero obtenerOCrearCajero(
            EmpleadoABM abm,
            List<Empleado> existentes,
            String nombre,
            String apellido,
            long dni,
            LocalDate fechaNacimiento,
            LocalDate fechaIngreso,
            double sueldoBase,
            String turno,
            UnidadVenta unidad) {

        Empleado existente =
                buscarEmpleado(existentes, dni);

        if (existente != null) {
            return (Cajero) existente;
        }

        Cajero empleado =
                new Cajero();

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDni(dni);
        empleado.setFechaNacimiento(
                fechaNacimiento);
        empleado.setFechaIngreso(
                fechaIngreso);
        empleado.setSueldoBase(
                sueldoBase);
        empleado.setTurno(
                turno);
        empleado.setUnidadVenta(
                unidad);

        abm.agregar(empleado);
        existentes.add(empleado);

        return empleado;
    }

    private static Cocinero obtenerOCrearCocinero(
            EmpleadoABM abm,
            List<Empleado> existentes,
            String nombre,
            String apellido,
            long dni,
            LocalDate fechaNacimiento,
            LocalDate fechaIngreso,
            double sueldoBase,
            String especialidad,
            double plusCategoria,
            UnidadVenta unidad) {

        Empleado existente =
                buscarEmpleado(existentes, dni);

        if (existente != null) {
            return (Cocinero) existente;
        }

        Cocinero empleado =
                new Cocinero();

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDni(dni);
        empleado.setFechaNacimiento(
                fechaNacimiento);
        empleado.setFechaIngreso(
                fechaIngreso);
        empleado.setSueldoBase(
                sueldoBase);
        empleado.setEspecialidadCulinaria(
                especialidad);
        empleado.setPlusCategoria(
                plusCategoria);
        empleado.setUnidadVenta(
                unidad);

        abm.agregar(empleado);
        existentes.add(empleado);

        return empleado;
    }

    private static Plato obtenerOCrearPlato(
            PlatoABM abm,
            List<Plato> existentes,
            String nombre,
            double precioVenta,
            double costoProduccion) {

        Plato plato =
                buscarPlato(existentes, nombre);

        if (plato == null) {

            plato = new Plato(
                    nombre,
                    precioVenta,
                    costoProduccion);

            abm.agregar(plato);
            existentes.add(plato);
        }

        return plato;
    }

    private static Pedido obtenerOCrearPedido(
            PedidoABM abm,
            List<Pedido> existentes,
            LocalDateTime fecha,
            Festival festival,
            UnidadVenta unidad) {

        Pedido pedido =
                buscarPedido(
                        existentes,
                        fecha,
                        festival,
                        unidad);

        if (pedido == null) {

            pedido = new Pedido(
                    fecha,
                    festival,
                    unidad);

            abm.agregar(pedido);
            existentes.add(pedido);
        }

        return pedido;
    }

    private static void agregarDosDetalles(
            DetallePedidoABM abm,
            Pedido pedido,
            Plato plato1,
            int cantidad1,
            Plato plato2,
            int cantidad2) {

        agregarDetalleSiNoExiste(
                abm,
                pedido,
                plato1,
                cantidad1,
                plato1.getPrecioVenta());

        agregarDetalleSiNoExiste(
                abm,
                pedido,
                plato2,
                cantidad2,
                plato2.getPrecioVenta());
    }

    private static void agregarDetalleSiNoExiste(
            DetallePedidoABM abm,
            Pedido pedido,
            Plato plato,
            int cantidad,
            double precioUnitario) {

        List<DetallePedido> detalles =
                abm.traer();

        for (DetallePedido detalle : detalles) {

            if (detalle.getPedido().getId()
                    == pedido.getId()
                    && detalle.getPlato().getId()
                    == plato.getId()) {

                return;
            }
        }

        DetallePedido detalle =
                new DetallePedido(
                        cantidad,
                        precioUnitario,
                        plato,
                        pedido);

        abm.agregar(detalle);
    }

    private static Festival buscarFestival(
            List<Festival> lista,
            String nombre) {

        for (Festival festival : lista) {

            if (festival.getNombre()
                    .equals(nombre)) {

                return festival;
            }
        }

        return null;
    }

    private static UnidadVenta buscarUnidad(
            List<UnidadVenta> lista,
            String codigo) {

        for (UnidadVenta unidad : lista) {

            if (unidad.getCodigo()
                    .equals(codigo)) {

                return unidad;
            }
        }

        return null;
    }

    private static Empleado buscarEmpleado(
            List<Empleado> lista,
            long dni) {

        for (Empleado empleado : lista) {

            if (empleado.getDni() == dni) {
                return empleado;
            }
        }

        return null;
    }

    private static Plato buscarPlato(
            List<Plato> lista,
            String nombre) {

        for (Plato plato : lista) {

            if (plato.getNombre()
                    .equals(nombre)) {

                return plato;
            }
        }

        return null;
    }

    private static Pedido buscarPedido(
            List<Pedido> lista,
            LocalDateTime fecha,
            Festival festival,
            UnidadVenta unidad) {

        for (Pedido pedido : lista) {

            if (pedido.getFechaTransaccion()
                    .equals(fecha)
                    && pedido.getFestival().getId()
                    == festival.getId()
                    && pedido.getUnidadDeVenta().getId()
                    == unidad.getId()) {

                return pedido;
            }
        }

        return null;
    }

    @SafeVarargs
    private static <T> ArrayList<T> lista(
            T... elementos) {

        return new ArrayList<T>(
                Arrays.asList(elementos));
    }

    private static void actualizarUnidades(
            UnidadVentaABM abm,
            UnidadVenta... unidades) {

        for (UnidadVenta unidad : unidades) {
            abm.actualizar(unidad);
        }
    }
}