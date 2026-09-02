package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class TestNico {

    public static void main(String[] args) {

        String nombreFestival = "Festival de la Primavera";

        try {

            // =====================================================
            // 1. ABM DEL SISTEMA
            // =====================================================

            FestivalABM festivalABM = new FestivalABM();
            PedidoABM pedidoABM = new PedidoABM();
            DetallePedidoABM detallePedidoABM = new DetallePedidoABM();
            UnidadVentaABM unidadVentaABM = new UnidadVentaABM();
            EmpleadoABM empleadoABM = new EmpleadoABM();
            PlatoABM platoABM = new PlatoABM();

            // =====================================================
            // 2. OBTENER DATOS DESDE LA BASE MEDIANTE ABM
            // =====================================================

            List<Festival> festivales = festivalABM.traer();
            List<Pedido> pedidos = pedidoABM.traer();
            List<DetallePedido> detalles = detallePedidoABM.traer();
            List<UnidadVenta> unidades = unidadVentaABM.traer();
            List<Empleado> empleados = empleadoABM.traer();
            List<Plato> platos = platoABM.traer();

            // =====================================================
            // 3. BUSCAR FESTIVAL
            // =====================================================

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

            // Mapa de platos cargados mediante PlatoABM
            Map<Integer, Plato> platosPorId =
                    new HashMap<Integer, Plato>();

            for (Plato plato : platos) {
                platosPorId.put(plato.getId(), plato);
            }

            // =====================================================
            // 4. PEDIDOS Y UNIDADES DEL FESTIVAL
            // =====================================================

            Set<Integer> idsPedidosFestival =
                    new HashSet<Integer>();

            Set<Integer> idsUnidadesFestival =
                    new HashSet<Integer>();

            for (Pedido pedido : pedidos) {

                if (pedido.getFestival() != null
                        && pedido.getFestival().getId()
                        == festival.getId()) {

                    idsPedidosFestival.add(
                            pedido.getId());

                    if (pedido.getUnidadDeVenta() != null) {

                        idsUnidadesFestival.add(
                                pedido.getUnidadDeVenta().getId());
                    }
                }
            }

            long cantidadPedidos =
                    idsPedidosFestival.size();

            List<UnidadVenta> unidadesFestival =
                    new ArrayList<UnidadVenta>();

            for (UnidadVenta unidad : unidades) {

                if (idsUnidadesFestival.contains(
                        unidad.getId())) {

                    unidadesFestival.add(unidad);
                }
            }

            // =====================================================
            // 5. VENTAS Y COSTO DE PRODUCCION
            // =====================================================

            long cantidadPlatosVendidos = 0;
            double recaudacionBruta = 0;
            double costoProduccion = 0;

            Map<Integer, Integer> cantidadVendidaPorPlato =
                    new HashMap<Integer, Integer>();

            Map<Integer, Double> recaudacionPorPlato =
                    new HashMap<Integer, Double>();

            for (DetallePedido detalle : detalles) {

                if (detalle.getPedido() == null
                        || !idsPedidosFestival.contains(
                                detalle.getPedido().getId())) {

                    continue;
                }

                int cantidad =
                        detalle.getCantidad();

                double recaudacionDetalle =
                        cantidad
                        * detalle.getPrecioUnitario();

                cantidadPlatosVendidos +=
                        cantidad;

                recaudacionBruta +=
                        recaudacionDetalle;

                if (detalle.getPlato() != null) {

                    int idPlato =
                            detalle.getPlato().getId();

                    Plato plato =
                            platosPorId.get(idPlato);

                    if (plato != null) {

                        costoProduccion +=
                                cantidad
                                * plato.getCostoProduccion();
                    }

                    int cantidadAnterior =
                            cantidadVendidaPorPlato
                            .getOrDefault(
                                    idPlato,
                                    0);

                    cantidadVendidaPorPlato.put(
                            idPlato,
                            cantidadAnterior
                            + cantidad);

                    double recaudacionAnterior =
                            recaudacionPorPlato
                            .getOrDefault(
                                    idPlato,
                                    0.0);

                    recaudacionPorPlato.put(
                            idPlato,
                            recaudacionAnterior
                            + recaudacionDetalle);
                }
            }

            // =====================================================
            // 6. COSTOS OPERATIVOS DE LAS UNIDADES
            // =====================================================

            double costoSuperficieTotal = 0;
            double costoMontajeTotal = 0;
            double costoElectricidadTotal = 0;

            int cantidadFoodTrucks = 0;
            int cantidadPuestosDesarmables = 0;
            int foodTrucksConElectricidad = 0;
            int tiempoMontajeTotal = 0;

            for (UnidadVenta unidad :
                    unidadesFestival) {

                // Costo por superficie
                costoSuperficieTotal +=
                        unidad.getSuperficie()
                        * festival.getCostoPorSuperficie();

                // Herencia UnidadVenta -> FoodTruck
                if (unidad instanceof FoodTruck) {

                    cantidadFoodTrucks++;

                    FoodTruck foodTruck =
                            (FoodTruck) unidad;

                    if (foodTruck
                            .isRequiereElectricidad()) {

                        foodTrucksConElectricidad++;

                        costoElectricidadTotal +=
                                festival
                                .getPlusUsoElectricidad();
                    }
                }

                // Herencia UnidadVenta -> PuestoDesarmable
                if (unidad
                        instanceof PuestoDesarmable) {

                    cantidadPuestosDesarmables++;

                    PuestoDesarmable puesto =
                            (PuestoDesarmable) unidad;

                    tiempoMontajeTotal +=
                            puesto.getTiempoMontaje();

                    costoMontajeTotal +=
                            festival.getCostoPorMontaje();
                }
            }

            // =====================================================
            // 7. PERSONAL DEL FESTIVAL
            // =====================================================

            Set<Integer> empleadosContados =
                    new HashSet<Integer>();

            double nominaMensualAsignada = 0;
            long cantidadCocineros = 0;
            double plusCocineros = 0;

            for (Empleado empleado :
                    empleados) {

                if (empleado.getUnidadVenta() == null
                        || !idsUnidadesFestival.contains(
                                empleado
                                .getUnidadVenta()
                                .getId())) {

                    continue;
                }

                if (empleadosContados.add(
                        empleado.getId())) {

                    nominaMensualAsignada +=
                            empleado.getSueldoBase();
                }

                // Herencia Empleado -> Cocinero
                if (empleado instanceof Cocinero) {

                    cantidadCocineros++;

                    Cocinero cocinero =
                            (Cocinero) empleado;

                    plusCocineros +=
                            cocinero
                            .getPlusCategoria();
                }
            }

            int cantidadEmpleados =
                    empleadosContados.size();

            // =====================================================
            // 8. PLATO MAS VENDIDO
            // =====================================================

            Plato platoMasVendido = null;
            int mayorCantidadVendida = 0;

            double recaudacionPlatoMasVendido =
                    0;

            for (Map.Entry<Integer, Integer> entrada :
                    cantidadVendidaPorPlato.entrySet()) {

                int idPlato =
                        entrada.getKey();

                int cantidadVendida =
                        entrada.getValue();

                if (cantidadVendida
                        > mayorCantidadVendida) {

                    Plato plato =
                            platosPorId.get(idPlato);

                    if (plato != null) {

                        platoMasVendido =
                                plato;

                        mayorCantidadVendida =
                                cantidadVendida;

                        recaudacionPlatoMasVendido =
                                recaudacionPorPlato
                                .getOrDefault(
                                        idPlato,
                                        0.0);
                    }
                }
            }

            // =====================================================
            // 9. CALCULOS ECONOMICOS
            // =====================================================

            double margenBruto =
                    recaudacionBruta
                    - costoProduccion;

            double costosOperativos =
                    costoSuperficieTotal
                    + costoMontajeTotal
                    + costoElectricidadTotal;

            double resultadoOperativoEstimado =
                    recaudacionBruta
                    - costoProduccion
                    - costosOperativos;

            double ticketPromedio =
                    cantidadPedidos > 0
                    ? recaudacionBruta
                            / cantidadPedidos
                    : 0;

            double platosPromedioPorPedido =
                    cantidadPedidos > 0
                    ? (double)
                            cantidadPlatosVendidos
                            / cantidadPedidos
                    : 0;

            double margenPorcentual =
                    recaudacionBruta > 0
                    ? resultadoOperativoEstimado
                            / recaudacionBruta
                            * 100
                    : 0;

            // =====================================================
            // 10. MOSTRAR INFORME
            // =====================================================

            System.out.println();

            System.out.println(
                    "============================================================");

            System.out.println(
                    "       INFORME ECONOMICO INTEGRAL DEL FESTIVAL");

            System.out.println(
                    "============================================================");

            System.out.println(
                    "Festival: "
                    + festival.getNombre());

            System.out.println(
                    "Temporada: "
                    + festival.getTemporada());

            System.out.println(
                    "Fecha: "
                    + festival.getFechaInicio()
                    + " al "
                    + festival.getFechaFin());

            System.out.println();

            System.out.println(
                    "---------------- VENTAS ----------------");

            System.out.println(
                    "Cantidad de pedidos: "
                    + cantidadPedidos);

            System.out.println(
                    "Cantidad total de platos vendidos: "
                    + cantidadPlatosVendidos);

            System.out.printf(
                    "Recaudacion bruta: $%.2f%n",
                    recaudacionBruta);

            System.out.printf(
                    "Ticket promedio: $%.2f%n",
                    ticketPromedio);

            System.out.printf(
                    "Promedio de platos por pedido: %.2f%n",
                    platosPromedioPorPedido);

            System.out.println();

            System.out.println(
                    "------------- RENTABILIDAD -------------");

            System.out.printf(
                    "Costo de produccion de platos: $%.2f%n",
                    costoProduccion);

            System.out.printf(
                    "Margen bruto de ventas: $%.2f%n",
                    margenBruto);

            System.out.println();

            System.out.println(
                    "---------- UNIDADES DE VENTA -----------");

            System.out.println(
                    "Unidades participantes: "
                    + unidadesFestival.size());

            System.out.println(
                    "Food Trucks: "
                    + cantidadFoodTrucks);

            System.out.println(
                    "Puestos Desarmables: "
                    + cantidadPuestosDesarmables);

            System.out.println(
                    "Food Trucks que requieren electricidad: "
                    + foodTrucksConElectricidad);

            System.out.println(
                    "Tiempo total de montaje de puestos: "
                    + tiempoMontajeTotal
                    + " minutos");

            System.out.println();

            System.out.println(
                    "--------- COSTOS DEL FESTIVAL ----------");

            System.out.printf(
                    "Costo total por superficie: $%.2f%n",
                    costoSuperficieTotal);

            System.out.printf(
                    "Costo total por montaje: $%.2f%n",
                    costoMontajeTotal);

            System.out.printf(
                    "Plus total por electricidad: $%.2f%n",
                    costoElectricidadTotal);

            System.out.printf(
                    "Costos operativos totales: $%.2f%n",
                    costosOperativos);

            System.out.println();

            System.out.println(
                    "--------------- PERSONAL ---------------");

            System.out.println(
                    "Cantidad de empleados asignados: "
                    + cantidadEmpleados);

            System.out.println(
                    "Cantidad de cocineros: "
                    + cantidadCocineros);

            System.out.printf(
                    "Nomina mensual asignada: $%.2f%n",
                    nominaMensualAsignada);

            System.out.printf(
                    "Plus de categorias de cocineros: $%.2f%n",
                    plusCocineros);

            System.out.printf(
                    "Sueldo base del festival: $%.2f%n",
                    festival.getSueldoBase());

            System.out.println();

            System.out.println(
                    "----------- PLATO MAS VENDIDO ----------");

            if (platoMasVendido != null) {

                System.out.println(
                        "Plato: "
                        + platoMasVendido
                        .getNombre());

                System.out.println(
                        "Unidades vendidas: "
                        + mayorCantidadVendida);

                System.out.printf(
                        "Recaudacion generada: $%.2f%n",
                        recaudacionPlatoMasVendido);

            } else {

                System.out.println(
                        "No hubo ventas en el festival.");
            }

            System.out.println();

            System.out.println(
                    "----------- RESULTADO FINAL ------------");

            System.out.printf(
                    "Recaudacion bruta: $%.2f%n",
                    recaudacionBruta);

            System.out.printf(
                    "- Costo produccion: $%.2f%n",
                    costoProduccion);

            System.out.printf(
                    "- Costos operativos: $%.2f%n",
                    costosOperativos);

            System.out.println(
                    "----------------------------------------");

            System.out.printf(
                    "Resultado operativo estimado: $%.2f%n",
                    resultadoOperativoEstimado);

            System.out.printf(
                    "Margen operativo estimado: %.2f%%%n",
                    margenPorcentual);

            System.out.println();

            System.out.println(
                    "============================================================");

            System.out.println(
                    "       INFORME GENERADO CORRECTAMENTE");

            System.out.println(
                    "============================================================");

        } catch (Exception e) {

            System.err.println(
                    "ERROR AL GENERAR EL INFORME:");

            e.printStackTrace();
        }
    }
}