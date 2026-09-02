package test;

import java.util.List;

import datos.Festival;
import datos.UnidadVenta;
import negocio.FestivalABM;
import negocio.UnidadVentaABM;

public class TestCande {

    public static void main(String[] args) {

        String nombreFestival = "Festival de la Primavera";

        FestivalABM festivalABM = new FestivalABM();
        UnidadVentaABM unidadVentaABM = new UnidadVentaABM();

        try {

            // Buscar festival
            List<Festival> festivales = festivalABM.traer();

            Festival festival = null;

            for (Festival f : festivales) {

                if (f.getNombre().equalsIgnoreCase(nombreFestival)) {
                    festival = f;
                    break;
                }
            }

            if (festival == null) {
                System.out.println(
                        "No existe un festival con el nombre: "
                        + nombreFestival);
                return;
            }

            // Obtener ranking
            List<Object[]> ranking =
                    unidadVentaABM.traerRankingPorFestival(
                            festival.getId());

            // Mostrar resultado
            System.out.println();
            System.out.println("============================================================");
            System.out.println("       RANKING DE UNIDADES DE VENTA");
            System.out.println("       SEGUN RECAUDACION OBTENIDA");
            System.out.println("============================================================");
            System.out.println("Festival: " + festival.getNombre());
            System.out.println();

            if (ranking.isEmpty()) {

                System.out.println(
                        "El festival no posee pedidos registrados.");

            } else {

                int posicion = 1;

                for (Object[] fila : ranking) {

                    UnidadVenta unidad =
                            (UnidadVenta) fila[0];

                    double recaudacion =
                            ((Number) fila[1]).doubleValue();

                    System.out.println(
                            posicion + ". "
                            + unidad.getNombreComercial()
                            + " | Codigo: "
                            + unidad.getCodigo()
                            + " | Recaudacion: $"
                            + String.format("%.2f", recaudacion));

                    posicion++;
                }
            }

            System.out.println();
            System.out.println("============================================================");
            System.out.println("       RANKING GENERADO CORRECTAMENTE");
            System.out.println("============================================================");

        } catch (Exception e) {

            System.err.println(
                    "ERROR AL EJECUTAR EL RANKING DE UNIDADES DE VENTA:");

            e.printStackTrace();
        }
    }
}
