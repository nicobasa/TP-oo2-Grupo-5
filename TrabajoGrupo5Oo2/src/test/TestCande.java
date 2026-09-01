package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.HibernateUtil;
import datos.Empleado;
import datos.Festival;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadVenta;

public class TestCande {

	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		String nombreFestival = "Festival de la Primavera";

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			// =====================================================
			// 1. BUSCAR FESTIVAL
			// =====================================================

			Query<Festival> consultaFestival = session.createQuery(
					"from Festival f "
					+ "where f.nombre = :nombre",
					Festival.class);

			consultaFestival.setParameter("nombre", nombreFestival);

			Festival festival = consultaFestival.uniqueResult();

			if (festival == null) {
				System.out.println(
						"No existe un festival con el nombre: "
						+ nombreFestival);

				tx.commit();
				return;
			}


			// =====================================================
			// 2. RESUMEN GENERAL DE VENTAS
			// =====================================================

			Query<Object[]> consultaVentas = session.createQuery(
					"select "
					+ "count(distinct p.id), "
					+ "sum(d.cantidad), "
					+ "sum(d.cantidad * d.precioUnitario), "
					+ "sum(d.cantidad * pl.costoProduccion) "
					+ "from Pedido p "
					+ "join p.detalles d "
					+ "join d.plato pl "
					+ "where p.festival.id = :festivalId",
					Object[].class);

			consultaVentas.setParameter(
					"festivalId",
					festival.getId());

			Object[] resumenVentas =
					consultaVentas.getSingleResult();

			long cantidadPedidos =
					resumenVentas[0] != null
					? ((Number) resumenVentas[0]).longValue()
					: 0;

			long cantidadPlatosVendidos =
					resumenVentas[1] != null
					? ((Number) resumenVentas[1]).longValue()
					: 0;

			double recaudacionBruta =
					resumenVentas[2] != null
					? ((Number) resumenVentas[2]).doubleValue()
					: 0;

			double costoProduccion =
					resumenVentas[3] != null
					? ((Number) resumenVentas[3]).doubleValue()
					: 0;


			// =====================================================
			// 3. UNIDADES PARTICIPANTES
			// =====================================================

			Query<UnidadVenta> consultaUnidades =
					session.createQuery(
							"select distinct u "
							+ "from UnidadVenta u "
							+ "join u.festivales f "
							+ "where f.id = :festivalId",
							UnidadVenta.class);

			consultaUnidades.setParameter(
					"festivalId",
					festival.getId());

			List<UnidadVenta> unidades =
					consultaUnidades.getResultList();


			// =====================================================
			// 4. COSTOS OPERATIVOS Y PERSONAL
			// =====================================================

			double costoSuperficieTotal = 0;
			double costoMontajeTotal = 0;
			double costoElectricidadTotal = 0;

			int cantidadFoodTrucks = 0;
			int cantidadPuestosDesarmables = 0;
			int foodTrucksConElectricidad = 0;
			int tiempoMontajeTotal = 0;

			Set<Integer> empleadosContados =
					new HashSet<Integer>();

			double nominaMensualAsignada = 0;

			for (UnidadVenta unidad : unidades) {

				// Costo por superficie
				costoSuperficieTotal +=
						unidad.getSuperficie()
						* festival.getCostoPorSuperficie();


				// Food Trucks
				if (unidad instanceof FoodTruck) {

					cantidadFoodTrucks++;

					FoodTruck foodTruck =
							(FoodTruck) unidad;

					if (foodTruck.isRequiereElectricidad()) {

						foodTrucksConElectricidad++;

						costoElectricidadTotal +=
								festival.getPlusUsoElectricidad();
					}
				}


				// Puestos Desarmables
				if (unidad instanceof PuestoDesarmable) {

					cantidadPuestosDesarmables++;

					PuestoDesarmable puesto =
							(PuestoDesarmable) unidad;

					tiempoMontajeTotal +=
							puesto.getTiempoMontaje();

					costoMontajeTotal +=
							festival.getCostoPorMontaje();
				}


				// Personal de la unidad
				if (unidad.getEmpleados() != null) {

					for (Empleado empleado :
							unidad.getEmpleados()) {

						if (empleadosContados.add(
								empleado.getId())) {

							nominaMensualAsignada +=
									empleado.getSueldoBase();
						}
					}
				}
			}

			int cantidadEmpleados =
					empleadosContados.size();


			// =====================================================
			// 5. CANTIDAD DE COCINEROS DEL FESTIVAL
			// Usa directamente la herencia Empleado -> Cocinero
			// =====================================================

			Query<Long> consultaCocineros =
					session.createQuery(
							"select count(distinct c.id) "
							+ "from Cocinero c "
							+ "join c.unidadVenta u "
							+ "join u.festivales f "
							+ "where f.id = :festivalId",
							Long.class);

			consultaCocineros.setParameter(
					"festivalId",
					festival.getId());

			long cantidadCocineros =
					consultaCocineros.getSingleResult();


			// =====================================================
			// 6. PLUS TOTAL DE COCINEROS
			// =====================================================

			Query<Double> consultaPlusCocineros =
					session.createQuery(
							"select sum(c.plusCategoria) "
							+ "from Cocinero c "
							+ "join c.unidadVenta u "
							+ "join u.festivales f "
							+ "where f.id = :festivalId",
							Double.class);

			consultaPlusCocineros.setParameter(
					"festivalId",
					festival.getId());

			Double resultadoPlus =
					consultaPlusCocineros.uniqueResult();

			double plusCocineros =
					resultadoPlus != null
					? resultadoPlus
					: 0;


			// =====================================================
			// 7. PLATO MAS VENDIDO
			// =====================================================

			Query<Object[]> consultaPlatoMasVendido =
					session.createQuery(
							"select "
							+ "pl.nombre, "
							+ "sum(d.cantidad), "
							+ "sum(d.cantidad * d.precioUnitario) "
							+ "from DetallePedido d "
							+ "join d.pedido p "
							+ "join d.plato pl "
							+ "where p.festival.id = :festivalId "
							+ "group by pl.id, pl.nombre "
							+ "order by sum(d.cantidad) desc",
							Object[].class);

			consultaPlatoMasVendido.setParameter(
					"festivalId",
					festival.getId());

			consultaPlatoMasVendido.setMaxResults(1);

			Object[] platoMasVendido =
					consultaPlatoMasVendido.uniqueResult();


			// =====================================================
			// 8. CALCULOS ECONOMICOS
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
					? recaudacionBruta / cantidadPedidos
					: 0;

			double platosPromedioPorPedido =
					cantidadPedidos > 0
					? (double) cantidadPlatosVendidos
							/ cantidadPedidos
					: 0;

			double margenPorcentual =
					recaudacionBruta > 0
					? resultadoOperativoEstimado
							/ recaudacionBruta * 100
					: 0;


			// =====================================================
			// 9. MOSTRAR INFORME
			// Todas las consultas ya fueron realizadas.
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
					+ unidades.size());

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
						+ platoMasVendido[0]);

				System.out.println(
						"Unidades vendidas: "
						+ ((Number) platoMasVendido[1])
								.longValue());

				System.out.printf(
						"Recaudacion generada: $%.2f%n",
						((Number) platoMasVendido[2])
								.doubleValue());

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

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}

			System.err.println(
					"ERROR AL GENERAR EL INFORME:");

			e.printStackTrace();

		} finally {

			if (session != null) {
				session.close();
			}
		}
	}
}