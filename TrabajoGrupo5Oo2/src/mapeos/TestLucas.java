package test;

import java.util.List;

import datos.Plato;
import negocio.PlatoABM;

public class TestLucas {

public static void main(String[] args) {

    PlatoABM platoABM = new PlatoABM();

    // TRAER TODOS LOS PLATOS DE LA BASE DE DATOS
    List<Plato> platos = platoABM.traer();

    System.out.println("========== INFORME DE PLATOS ==========");

    if (platos.isEmpty()) {
        System.out.println("No hay platos registrados.");
        return;
    }

    double sumaPrecios = 0;
    double sumaGanancias = 0;

    Plato platoMasCaro = platos.get(0);
    Plato platoMayorGanancia = platos.get(0);
    Plato platoMejorMargen = platos.get(0);

    for (Plato plato : platos) {

        double precio = plato.getPrecioVenta();
        double costo = plato.getCostoProduccion();
        double ganancia = precio - costo;

        double margen = 0;

        if (precio > 0) {
            margen = (ganancia / precio) * 100;
        }

        System.out.println(
                "ID: " + plato.getId()
                + " | " + plato.getNombre()
                + " | Precio: $" + precio
                + " | Costo: $" + costo
                + " | Ganancia: $" + ganancia
                + " | Margen: " + String.format("%.2f", margen) + "%"
        );

        // Acumular datos para los promedios
        sumaPrecios += precio;
        sumaGanancias += ganancia;

        // Plato más caro
        if (precio > platoMasCaro.getPrecioVenta()) {
            platoMasCaro = plato;
        }

        // Plato con mayor ganancia
        double gananciaActualMayor =
                platoMayorGanancia.getPrecioVenta()
                - platoMayorGanancia.getCostoProduccion();

        if (ganancia > gananciaActualMayor) {
            platoMayorGanancia = plato;
        }

        // Plato con mejor margen
        double gananciaMejorMargen =
                platoMejorMargen.getPrecioVenta()
                - platoMejorMargen.getCostoProduccion();

        double margenMejor = 0;

        if (platoMejorMargen.getPrecioVenta() > 0) {
            margenMejor =
                    (gananciaMejorMargen
                    / platoMejorMargen.getPrecioVenta()) * 100;
        }

        if (margen > margenMejor) {
            platoMejorMargen = plato;
        }
    }

    // PROMEDIOS
    double precioPromedio = sumaPrecios / platos.size();
    double gananciaPromedio = sumaGanancias / platos.size();

    System.out.println();
    System.out.println("========== ESTADISTICAS ==========");

    System.out.println(
            "Cantidad de platos: " + platos.size()
    );

    System.out.println(
            "Precio promedio: $"
            + String.format("%.2f", precioPromedio)
    );

    System.out.println(
            "Ganancia promedio: $"
            + String.format("%.2f", gananciaPromedio)
    );

    // RESULTADOS
    double gananciaMayor =
            platoMayorGanancia.getPrecioVenta()
            - platoMayorGanancia.getCostoProduccion();

    double gananciaMejor =
            platoMejorMargen.getPrecioVenta()
            - platoMejorMargen.getCostoProduccion();

    double mejorMargen =
            (gananciaMejor / platoMejorMargen.getPrecioVenta()) * 100;

    System.out.println();
    System.out.println("========== DESTACADOS ==========");

    System.out.println(
            "Plato más caro: "
            + platoMasCaro.getNombre()
            + " ($"
            + platoMasCaro.getPrecioVenta()
            + ")"
    );

    System.out.println(
            "Mayor ganancia: "
            + platoMayorGanancia.getNombre()
            + " ($"
            + gananciaMayor
            + ")"
    );

    System.out.println(
            "Mejor margen: "
            + platoMejorMargen.getNombre()
            + " ("
            + String.format("%.2f", mejorMargen)
            + "%)"
    );
}


}
