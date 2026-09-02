package test;

import java.util.List;
import datos.DetallePedido;
import datos.FoodTruck;
import datos.Pedido;
import datos.PuestoDesarmable;
import negocio.PedidoABM;

public class TestIvan {

    public static void main(String[] args) {
        
        double montoMinimo = 12000.0; 
        PedidoABM pedidoABM = new PedidoABM();

        try {
            List<Pedido> pedidosFinales = pedidoABM.traerPedidosPremium(montoMinimo);
            
            System.out.println("=================================================================");
            System.out.printf("  REPORTE DE PEDIDOS PREMIUM (SUPERIORES A $%.2f)%n", montoMinimo);
            System.out.println("=================================================================");
            
            if (pedidosFinales.isEmpty()) {
                System.out.println("No se encontraron pedidos superiores a $" + montoMinimo);
                return;
            }

            for (Pedido p : pedidosFinales) {
                double totalVenta = 0;
                double totalCosto = 0;

                System.out.println("\n[ PEDIDO ID: " + p.getId() + " | Fecha: " + p.getFechaTransaccion() + " ]");
                System.out.println("-> Festival: " + p.getFestival().getNombre());
                
                String tipoUnidad = "Desconocido";
                if (p.getUnidadDeVenta() instanceof FoodTruck) {
                    tipoUnidad = "FoodTruck";
                } else if (p.getUnidadDeVenta() instanceof PuestoDesarmable) {
                    tipoUnidad = "Puesto Desarmable";
                }
                
                System.out.println("-> Unidad de Venta: " + p.getUnidadDeVenta().getNombreComercial() + " (" + tipoUnidad + ")");
                System.out.println("-> Detalle de Platos:");

                for (DetallePedido d : p.getDetalles()) {
                    double subtotalVenta = d.getCantidad() * d.getPrecioUnitario();
                    double subtotalCosto = d.getCantidad() * d.getPlato().getCostoProduccion();
                    
                    totalVenta += subtotalVenta;
                    totalCosto += subtotalCosto;

                    System.out.printf("   * %dx %-20s | Venta: $%-7.2f (Costo prod: $%.2f)%n",
                            d.getCantidad(),
                            d.getPlato().getNombre(),
                            subtotalVenta,
                            subtotalCosto);
                }
                
                double gananciaNeta = totalVenta - totalCosto;
                
                System.out.println("-----------------------------------------------------------------");
                System.out.printf("TOTAL FACTURADO: $%.2f | COSTO TOTAL: $%.2f | GANANCIA NETA: $%.2f%n", 
                                  totalVenta, totalCosto, gananciaNeta);
                System.out.println("=================================================================");
            }

        } catch (Exception e) {
            System.err.println("ERROR AL EJECUTAR EL REPORTE DE IVAN:");
            e.printStackTrace();
        }
    }
}