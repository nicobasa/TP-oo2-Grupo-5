package test;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.HibernateUtil;
import datos.DetallePedido;
import datos.FoodTruck;
import datos.Pedido;
import datos.PuestoDesarmable;

public class TestIvan {

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        Session session = null;
        Transaction tx = null;
        
        //Filtra pedidos que superen este monto:
        double montoMinimo = 12000.0; 

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // 1. CONSULTA AL HQL:
            // Se interactua con Pedido y DetallePedido para sumar montos.
            String hqlIds = "SELECT p.id FROM DetallePedido d "
                          + "JOIN d.pedido p "
                          + "GROUP BY p.id "
                          + "HAVING SUM(d.cantidad * d.precioUnitario) > :montoMinimo";
            
            Query<Integer> queryIds = session.createQuery(hqlIds, Integer.class);
            queryIds.setParameter("montoMinimo", montoMinimo);
            List<Integer> idsPedidosCaros = queryIds.getResultList();

            if (idsPedidosCaros.isEmpty()) {
                System.out.println("No se encontraron pedidos superiores a $" + montoMinimo);
                tx.commit();
                return;
            }

            // 2. OBTENCION DE INFORMACION DE TABLAS:
            // Interaccion de Pedido, Festival, UnidadVenta, DetallePedido y Plato.
            String hqlPedidos = "SELECT DISTINCT p FROM Pedido p "
                              + "JOIN FETCH p.festival "
                              + "JOIN FETCH p.unidadDeVenta "
                              + "JOIN FETCH p.detalles d "
                              + "JOIN FETCH d.plato "
                              + "WHERE p.id IN (:ids) "
                              + "ORDER BY p.fechaTransaccion DESC";

            Query<Pedido> queryPedidos = session.createQuery(hqlPedidos, Pedido.class);
            queryPedidos.setParameterList("ids", idsPedidosCaros);
            List<Pedido> pedidosFinales = queryPedidos.getResultList();

            // 3. PROCESAMIENTO Y REPORTE:
            // Se muestra los pedidos finales de la gran consulta realizada.
            System.out.println("=================================================================");
            System.out.printf("  REPORTE DE PEDIDOS PREMIUM (SUPERIORES A $%.2f)%n", montoMinimo);
            System.out.println("=================================================================");

            for (Pedido p : pedidosFinales) {
                double totalVenta = 0;
                double totalCosto = 0;

                System.out.println("\n[ PEDIDO ID: " + p.getId() + " | Fecha: " + p.getFechaTransaccion() + " ]");
                System.out.println("-> Festival: " + p.getFestival().getNombre());
                
                // Se aplica polimorfismo/herencia para la Unidad de Venta (FoodTruck y PuestoDesarmable).
                String tipoUnidad = "Desconocido";
                if (p.getUnidadDeVenta() instanceof FoodTruck) {
                	tipoUnidad = "FoodTruck";
                }
                
                if (p.getUnidadDeVenta() instanceof PuestoDesarmable) {
                	tipoUnidad = "Puesto Desarmable";
                }
                
                System.out.println("-> Unidad de Venta: " + p.getUnidadDeVenta().getNombreComercial() + " (" + tipoUnidad + ")");
                System.out.println("-> Detalle de Platos:");

                for (DetallePedido d : p.getDetalles()) {
                    double subtotalVenta = d.getCantidad() * d.getPrecioUnitario();
                    double subtotalCosto = d.getCantidad() * d.getPlato().getCostoProduccion();
                    
                    totalVenta += subtotalVenta;
                    totalCosto += subtotalCosto;

                    System.out.printf("   * %dx %-20s | Venta: $%-7.2f (Costo de prod: $%.2f)%n",
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

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("ERROR AL EJECUTAR EL CASO DE USO DE IVAN:");
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}